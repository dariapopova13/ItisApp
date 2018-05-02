import Vue from 'vue'
import Router from 'vue-router'
import News from './components/News'
import Courses from './components/Courses'
import Me from './components/Me'
import Professor from './components/Professor'
import AddNews from './components/AddNews'
import EditNews from './components/EditNews'

Vue.use(Router);
export default new Router({
    routes: [
        // {
        //     path: '/login',
        //     name: 'login',
        //     component: Login
        // },
        {
            path: '/me',
            name: 'me',
            component: Me
        },
        {
            path: '/news',
            name: 'news',
            component: News
        },
        {
            path: '/courses',
            name: 'courses',
            component: Courses
        },
        {
            path: '/professors',
            name: 'professors',
            component: Professor
        },
        {
            path: '/news/edit/:id',
            name: 'news edit',
            component: EditNews,
            props: true
        },
        {
            path: '/news/add',
            name: 'news add',
            component: AddNews
        },
        {
            path: "/",
            redirect: {
                name: "news"
            }
        },
    ]
})