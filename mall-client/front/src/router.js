
import MenuList from "./page/permit/role/MenuList";
import Home from "./page/home/Home";
import RoleList from "./page/permit/role/RoleList";
import Spike from "./page/activity/spike/Spike";

export default [
    {
        title: '首页',
        path: '/home',
        component: Home,
    },
    {
        title: '菜单列表',
        path: '/menuList',
        component: MenuList,
    },
    {
        title: '菜单列表',
        path: '/spike',
        component: Spike,
    },
    {
        title: '角色列表',
        path: '/roleList',
        component: RoleList,
    },
]
