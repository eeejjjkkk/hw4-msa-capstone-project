# 캡스톤 프로젝트 주제 : 퀵서비스

#### 참여자 : 김은정, 윤진선, 이상원

### 서비스 시나리오
#### 기능적 요구사항 
1. 고객이 물품의 크기, 무게, 도착지를 선택하여 접수한다.
2. 고객이 결제수단(착불, 선불)을 선택한다.
3. 접수가 되면 접수 내역이 퀵서비스 업체에 전달된다.
4. 접수처에서 확인하여 배송기사를 출발한다.
5. 고객이 접수를 취소할 수 있다.
6. 접수가 취소되면 배달이 취소된다.
7. 고객이 진행 상태를 중간중간 조회한다.
8. 진행 상태가 바뀔 때마다 카톡으로 알림을 보낸다.

#### 비기능적 요구사항
store에서 승인하지 않은 주문건은 거래가 성립되지 않아야 한다 Sync 호출

#### 장애격리
상점관리 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다 
Async (event-driven), Eventual Consistency

### 분석/설계 

#### 완성된 모형
![캡처](/images/model1.PNG)

#### 완성본에 대한 기능적/비기능적 요구사항을 커버하는지 검증
![캡처](/images/model2.PNG)

```
-고객이 퀵서비스를 접수한다 (ok)
-퀵서비스 업체에서 accept한다 (ok)
- 퀵서비스 업체에서 assign 하면 배달 기사가 배정된다 (ok)
-배달 기사가 accept하면 배달을 출발한다 (ok)
-도착지의 고객이 수취 완료를 한다 (ok)
```

#### 헥사고날 아키텍처 다이어그램 도출
![캡처](/images/hex.PNG)

#### 트랙잭션 처리
```
-고객 접수시 퀵서비스 접수 리스트에 추가
-퀵서비스업체 assign시 배달 리스트 추가
-접수, 배송상태 알림
```

### SAGA
#### 구현
각 서비스별로 개별 실행한다.
```
cd order/
mvn spring-boot:run
```

```
cd store/
mvn spring-boot:run
```

```
cd gateway/
mvn spring-boot:run
```

```
cd delivery/
mvn spring-boot:run
```

```
cd dashboard/
mvn spring-boot:run
```

### CQRS Pattern
* 주문 조회, 주문 요청
```
http http://localhost:8088/orders
http POST localhost:8088/orders address="서울특별시 마포구" destination="인천광역시 부평구" iteminfo="서류" payment="후불" orderId="1" 
```
```
HTTP/1.1 201 Created
Content-Type: application/json
Date: Wed, 27 Jul 2022 06:59:24 GMT
Location: http://localhost:8081/orders/1
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_links": {
        "order": {
            "href": "http://localhost:8081/orders/1"
        },
        "self": {
            "href": "http://localhost:8081/orders/1"
        }
    },
    "address": "서울특별시 마포구",
    "destination": "인천광역시 부평구",
    "iteminfo": "서류",
    "orderId": "1",
    "payment": "후불"
}
```

#### 카프카
```
/usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server.properties
```
```
{"eventType":"OrderPlaced","timestamp":1658900433011,"id":3368,"address":"경기도","destination":"수원","payment":"후불","iteminfo":"서류","orderId":"1"}
```

#### 마이페이지 조회
-dashboard 기능을 통해 배송 상태를 조회할 수 있다 
```
http GET localhost:8088/myPages/
```
```
HTTP/1.1 200 OK
Content-Type: application/hal+json
Date: Wed, 27 Jul 2022 07:04:00 GMT
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_embedded": {
        "myPages": [
            {
                "_links": {
                    "myPage": {
                        "href": "http://localhost:8085/myPages/2"
                    },
                    "self": {
                        "href": "http://localhost:8085/myPages/2"
                    }
                },
                "status": "접수요청"
            }
        ]
    },
    "_links": {
        "profile": {
            "href": "http://localhost:8085/profile/myPages"
        },
        "self": {
            "href": "http://localhost:8085/myPages/"
        }
    },
    "page": {
        "number": 0,
        "size": 20,
        "totalElements": 1,
        "totalPages": 1
    }
}
```

### Gateway 
-Gateway 기능을 통해 진입점을 통일하여 해당 서비스로 라우팅 해준다.
-라우팅 정보는 application.yaml에서 확인한다.

```
spring:
  profiles: default
  cloud:
    gateway:
      routes:
        - id: order
          uri: http://localhost:8081
          predicates:
            - Path=/orders/** 
        - id: store
          uri: http://localhost:8082
          predicates:
            - Path=/storeOrders/** 
        - id: delivery
          uri: http://localhost:8083
          predicates:
            - Path=/deliveries/** 
        - id: Pay
          uri: http://localhost:8084
          predicates:
            - Path=/payLists/** 
        - id: dashboard
          uri: http://localhost:8085
          predicates:
            - Path= /myPages/**
        - id: frontend
          uri: http://localhost:8080
          predicates:
            - Path=/**
```

### Deploy
-deploy 테스트를 위해 도커 이미지 생성 및 배포
'''
cd order/
mvn package -B -Dmaven.test.skip=true
docker build -t eejjkk2017/order:0727 .     
docker push eejjkk2017/order:0727 
'''
'''
cd store/
mvn package -B -Dmaven.test.skip=true
docker build -t eejjkk2017/store:0727 .     
docker push eejjkk2017/store:0727 
'''
'''
cd gateway/
mvn package -B -Dmaven.test.skip=true
docker build -t eejjkk2017/gateway:0727 .     
docker push eejjkk2017/gateway:0727 
'''
'''
cd delivery/
mvn package -B -Dmaven.test.skip=true
docker build -t eejjkk2017/delivery:0727 .     
docker push eejjkk2017/delivery:0727 
'''

### Circuit Breaker
#### Circuit Breaker 테스트 환경설정 
1. 배송서비스의 Replica를 3개로 늘인다.
```
kubectl scale deploy delivery --replicas=3 -n istio-cb-ns
```

2. Terminal을 2개 더 준비한다. 배송 컨테이너 Pod 의 IP를 확인한다.
```
kubectl get pod -o wide -n istio-cb-ns
kubectl exec -it pod/siege-75d5587bf6-hwzdq -n istio-cb-ns -- /bin/bash
```
```
root@siege-75d5587bf6-hwzdq:/# http http://delivery:8080/actuator/echo
HTTP/1.1 200 
Content-Length: 40
Content-Type: text/plain;charset=UTF-8
Date: Wed, 27 Jul 2022 06:20:54 GMT

delivery-67ff6476bb-g64jz/192.168.74.126

root@siege-75d5587bf6-hwzdq:/# http http://delivery:8080/actuator/echo
HTTP/1.1 200 
Content-Length: 38
Content-Type: text/plain;charset=UTF-8
Date: Wed, 27 Jul 2022 06:20:57 GMT

delivery-67ff6476bb-lwplk/192.168.26.9

root@siege-75d5587bf6-hwzdq:/# http http://delivery:8080/actuator/echo
HTTP/1.1 200 
Content-Length: 40
Content-Type: text/plain;charset=UTF-8
Date: Wed, 27 Jul 2022 06:21:01 GMT

delivery-67ff6476bb-x4vqx/192.168.48.207

root@siege-75d5587bf6-hwzdq:/# http http://delivery:8080/actuator/echo
HTTP/1.1 200 
Content-Length: 40
Content-Type: text/plain;charset=UTF-8
Date: Wed, 27 Jul 2022 06:21:03 GMT

delivery-67ff6476bb-x4vqx/192.168.48.207
```

3. 새로운 터미널에서 Delivery 컨테이너로 접속하여 명시적으로 5xx 오류를 발생 시킨다.
```
kubectl exec -it pod/delivery-67ff6476bb-g64jz -n istio-cb-ns -c delivery -- /bin/sh
```
```
/ # apk update
/ # apk add httpie
/ # http PUT http://localhost:8080/actuator/down
```
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Wed, 27 Jul 2022 06:22:25 GMT
Transfer-Encoding: chunked

{
    "status": "DOWN"
}
```

4. Circuit Breaker 동작 확인
```
http GET http://delivery:8080/actuator/health
```
```
root@siege-75d5587bf6-hwzdq:/# http GET http://delivery:8080/actuator/health
HTTP/1.1 200 
Content-Type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8
Date: Wed, 27 Jul 2022 06:23:06 GMT
Transfer-Encoding: chunked

{
    "status": "UP"
}

root@siege-75d5587bf6-hwzdq:/# http GET http://delivery:8080/actuator/health
HTTP/1.1 503 
Connection: close
Content-Type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8
Date: Wed, 27 Jul 2022 06:23:08 GMT
Transfer-Encoding: chunked

{
    "status": "DOWN"
}
```

### Autoscale(HPA)
1. auto scaler 설정 및 설정값 확인
```
# auto scaler 설정
$ kubectl autoscale deployment order --cpu-percent=20 --min=1 --max=3

# 설정값 확인
$ kubectl get hpa
NAME    REFERENCE          TARGETS         MINPODS   MAXPODS   REPLICAS   AGE
order   Deployment/order   <unknown>/20%   1         3         1          50m 
```

2. deployment.yaml 파일 수정(아래 추가)
```
resources:
    limits:
      cpu: 500m
    requests:
      cpu: 200m
```

3. 배포
```
kubectl apply -f order/kubernetes/deployment.yaml 
```

4. auto scale 설정 테스트
-seige 명령으로 부하를 주어 pod 개수가 늘어나는 것을 확인

```
kubectl exec -it siege -- /bin/bash

# 결과 
Lifting the server siege...
Transactions:                   2130 hits
Availability:                 100.00 %
Elapsed time:                  39.33 secs
Data transferred:               0.04 MB
Response time:                  0.37 secs
Transaction rate:              54.16 trans/sec
Throughput:                     0.00 MB/sec
Concurrency:                   19.92
Successful transactions:        2155
Failed transactions:               0
Longest transaction:            0.42
Shortest transaction:           0.19
```

##### order pod 개수 증가 확인 
```
order-544d5cbfc-4s2dc       1/1     Running            0          5m13s
order-544d5cbfc-dmlkt       1/1     Running            0          5m13s
order-544d5cbfc-j29l8       1/1     Running            0          10m
```

##### cpu 정보 확인

```
NAME    REFERENCE          TARGETS   MINPODS   MAXPODS   REPLICAS   AGE
order   Deployment/order   120%/20%    1         3         3          109m
```
