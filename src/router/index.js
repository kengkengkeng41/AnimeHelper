import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),

    routes: [
        {
            path: '/',
            redirect: '/home'
        },
        {
            path: '/home',
            name: 'home',
            component: () => import('@/views/Home.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('@/views/Login.vue')
        },


        {
            path: '/about',
            name: 'about',
            component: () => import('@/views/About.vue')
        },

        {
            path: '/setting',
            name: 'setting',
            component: () => import('@/views/Setting.vue')
        },
        {
            path: '/user',
            name: 'user',
            component: () => import('@/views/User.vue')
        },
        {
            path: '/anime-detail',
            name: 'AnimeDetail',
            component: () => import('@/views/AnimeDetail.vue')
        },

        {
            path: '/browser',
            name: 'browser',
            component: () => import('@/views/EditFv.vue')
        },

        {   //轮播图浏览
            path: '/carousel',
            name: 'carousel',
            component: () => import('@/views/Carousel.vue')
        },

        {   //轮播图插入与编辑
            path: '/cins',
            name: 'cins',
            component: () => import('@/views/Cins.vue')
        },

        {
            path: '/animeFv',
            name: 'AnimeDetailFv',
            component: () => import('@/views/AnimeDetailFv.vue')
        },
        {
            path: '/eco',
            name: 'Eco',
            component: () => import('@/views/Eco.vue')
        },

        {
            path: '/logout',
            name: 'Logout',
            component: () => import('@/views/Logout.vue')
        },
        {
            path: '/visitor',
            name: 'Visitor',
            component: () => import('@/views/UserList.vue')
        },
        {
            path: '/edit',
            name: 'Editor',
            component: () => import('@/views/Edit.vue')
        },
        {
            path: '/updateA',
            name: 'UpdateAnime',
            component: () => import('@/views/UpdateAnime.vue')
        },
        {
            path: '/insertA',
            name: 'InsertAnime',
            component: () => import('@/views/InsertAnime.vue')
        },
    ]
})

export default router