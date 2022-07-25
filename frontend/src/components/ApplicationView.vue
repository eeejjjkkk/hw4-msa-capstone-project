<template>

    <v-data-table
        :headers="headers"
        :items="application"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ApplicationView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            application : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/applications'))

            temp.data._embedded.applications.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.application = temp.data._embedded.applications;
        },
        methods: {
        }
    }
</script>

