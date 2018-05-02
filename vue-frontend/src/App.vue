<template>
    <v-app id="inspire" >
        <v-navigation-drawer
                clipped
                fixed
                v-model="drawer"
                app>
            <v-list two-line>

                <v-list-tile to="/news">
                    <v-list-tile-action>
                        <v-icon>event_note</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Новости</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>


                <v-list-tile to="/courses">
                    <v-list-tile-action>
                        <v-icon>school</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>

                        <v-list-tile-title>Курсы</v-list-tile-title>

                    </v-list-tile-content>
                </v-list-tile>

                <v-list-tile to="/professors">
                    <v-list-tile-action>
                        <v-icon>people</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Преподователи</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
                <v-divider dark></v-divider>
                <v-list-tile to="/me">
                    <v-list-tile-action>
                        <v-icon>account_circle</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Личный кабинет</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>

            </v-list>
        </v-navigation-drawer>


        <v-toolbar color="primary" dark app flat fixed clipped-left>
            <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
            <v-toolbar-title>Высшая школа ИТИС</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-text-field
                    flat
                    solo-inverted
                    prepend-icon="search"
                    label="Search"
                    class="hidden-sm-and-down"
            ></v-text-field>
        </v-toolbar>

        <v-content>
            <v-container >
                <router-view />
            </v-container>
        </v-content >

        <v-footer height="auto" color="blue-grey lighten-5">
            <v-flex xs12 py-3 text-xs-center black--text>
                &copy;2018 — ItisApp
            </v-flex>
        </v-footer>
    </v-app>
</template>

<script>
    export default {
        data: () => ({
            drawer: true
        }),
        props: {
            source: String
        }
    }
</script>
