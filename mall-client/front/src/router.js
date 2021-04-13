import DownloadHistory from "./page/move/DownloadHistory";
import MenuList from "./page/menu/MenuList";

export default [
    {
        title: '菜单列表',
        path: '/menuList',
        component: MenuList,
    },
    {
        title: '历史文件',
        path: '/downloadHistory',
        component: DownloadHistory,
    },

]
