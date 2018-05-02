import Vue from 'vue'
import App from './App.vue'
import Vuetify from 'vuetify'
import VeeValidation from 'vee-validate'
import router from './router'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import axios from 'axios'
import 'w3-css/w3.css';



Vue.use(Vuetify, {
    theme: {
        primary: '#00549F',
        secondary: '#012A77',
        accent: '#FFC107'
    }
});
Vue.use(VeeValidation);

Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
