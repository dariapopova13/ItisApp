<template>
    <v-expansion-panel focusable expand>
        <v-expansion-panel-content v-for="(item, index) in courses" :key="index">
            <div slot="header">
                <h5 class="md-card-header">{{item.name}}</h5>
                <p class="md-subheader">{{item.prof}}</p>
            </div>

            <v-card>
                <v-card-text class="grey lighten-3">
                    {{item.info}}
                </v-card-text>
            </v-card>

        </v-expansion-panel-content>
    </v-expansion-panel>
</template>

<script>
    export default {
        name: "Courses",
        data() {
            return {
                courses: [{
                    name: "Course name",
                    prof: "Name surname",
                    info: "Course info"
                }, {
                    name: "Course name",
                    prof: "Name surname",
                    info: "Course info"
                }, {
                    name: "Course name",
                    prof: "Name surname",
                    info: "Course info"
                }, {
                    name: "Course name",
                    prof: "Name surname",
                    info: "Course info"
                }, {
                    name: "Course name" +
                    "Course name" +
                    "Course name" +
                    "Course nameCourse nameCourse name" +
                    "Course nameCougggggggggggggg" +
                    "ggggggggggggggggggrse nameCourse name",
                    prof: "Name surname",
                    info: "Course info"
                }, {
                    name: "Course name",
                    prof: "Name surname",
                    info: "Course info"
                },
                    {
                        name: "Course name",
                        prof: "Name surname",
                        info: "Course info"
                    },
                    {
                        name: "Course name",
                        prof: "Name surname",
                        info: "Course info"
                    }, {
                        name: "Course name",
                        prof: "Name surname",
                        info: "Course info"
                    }
                    , {
                        name: "Course name",
                        prof: "Name surname",
                        info: "Course info"
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>
