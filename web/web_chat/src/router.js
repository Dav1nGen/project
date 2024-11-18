import { createRouter, createWebHistory } from 'vue-router';
import Login from './components/Login.vue';
import ChatGroups from './views/ChatGroups.vue';
import ChatRoomView from './views/ChatRoomView.vue';

const routes = [
  { path: '/', component: Login },
  { path: '/groups', component: ChatGroups },
  { path: '/chat/:id', component: ChatRoomView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;