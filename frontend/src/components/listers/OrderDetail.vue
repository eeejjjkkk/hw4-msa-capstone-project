<template>
    <v-card outlined>
        <v-card-title>
            Order # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="Address" v-model="item.address" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Destination" v-model="item.destination" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Iteminfo" v-model="item.iteminfo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Payment" v-model="item.payment" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="OrderId" v-model="item.orderId" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/orders/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
        },
    };
</script>
