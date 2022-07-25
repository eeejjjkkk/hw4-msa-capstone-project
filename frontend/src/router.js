
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"

import ApplicationView from "./components/ApplicationView"
import ApplicationViewDetail from "./components/ApplicationViewDetail"
import StoreOrderManager from "./components/listers/StoreOrderCards"
import StoreOrderDetail from "./components/listers/StoreOrderDetail"

import OrderDetailView from "./components/OrderDetailView"
import OrderDetailViewDetail from "./components/OrderDetailViewDetail"
import DeliveryManager from "./components/listers/DeliveryCards"
import DeliveryDetail from "./components/listers/DeliveryDetail"

import PayHistoryManager from "./components/listers/PayHistoryCards"
import PayHistoryDetail from "./components/listers/PayHistoryDetail"


import MyPageView from "./components/MyPageView"
import MyPageViewDetail from "./components/MyPageViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },

            {
                path: '/applications',
                name: 'ApplicationView',
                component: ApplicationView
            },
            {
                path: '/applications/:id',
                name: 'ApplicationViewDetail',
                component: ApplicationViewDetail
            },
            {
                path: '/storeOrders',
                name: 'StoreOrderManager',
                component: StoreOrderManager
            },
            {
                path: '/storeOrders/:id',
                name: 'StoreOrderDetail',
                component: StoreOrderDetail
            },

            {
                path: '/orderDetails',
                name: 'OrderDetailView',
                component: OrderDetailView
            },
            {
                path: '/orderDetails/:id',
                name: 'OrderDetailViewDetail',
                component: OrderDetailViewDetail
            },
            {
                path: '/deliveries',
                name: 'DeliveryManager',
                component: DeliveryManager
            },
            {
                path: '/deliveries/:id',
                name: 'DeliveryDetail',
                component: DeliveryDetail
            },

            {
                path: '/payHistories',
                name: 'PayHistoryManager',
                component: PayHistoryManager
            },
            {
                path: '/payHistories/:id',
                name: 'PayHistoryDetail',
                component: PayHistoryDetail
            },


            {
                path: '/myPages',
                name: 'MyPageView',
                component: MyPageView
            },
            {
                path: '/myPages/:id',
                name: 'MyPageViewDetail',
                component: MyPageViewDetail
            },


    ]
})
