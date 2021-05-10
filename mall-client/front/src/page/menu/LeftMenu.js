import React from 'react';
import {Link, Route, Switch, withRouter} from "react-router-dom";
import {Avatar, Layout, Menu, Modal, PageHeader, Select} from 'antd';
import HttpUtil from "../../utils/HttpUtil";
import Api from "../../common/Api";
import * as Icon from '@ant-design/icons'
import {GithubOutlined} from '@ant-design/icons'
import routers from "../../router";
import "./menu.scss"
import cookie from 'react-cookies'
import Constant from "../../common/Constant";
const {Header, Content, Footer, Sider} = Layout;
const {SubMenu} = Menu;
const {Option} = Select;

class LeftMenu extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            menuTreeNode: [],
            showDownload: false,
            showDownloadHistory: false,
        };
    }

    componentWillMount() {
        HttpUtil.get(Api.findMenuList,this.props).then(response => {
            if (response.status === "200") {
                this.setState({
                    menuTreeNode: response.data
                })
            } else {
                Modal.error({
                    title: '获取菜单失败',
                    content: '无法加载菜单，请联系管理员'
                })
            }
        })
    }

    /**
     * 退出登录
     */
    loginOut = () => {
        // 删除cookie
        cookie.remove(Constant.COOKIE_LOGIN_KEY);
        HttpUtil.get(Api.loginOut,this.props).then(response => {
            this.props.history.push("/login");
        });

    };

    render() {
        const {menuTreeNode = []} = this.state;

        // 菜单treeNode
        let menuItemList = menuTreeNode.map(tree => {
            return (
                <SubMenu
                    key={tree.title}
                    title={tree.title}
                    icon={
                        // TODO 没有图标会报错
                        React.createElement(
                            Icon[tree.icon]
                        )
                    }
                >
                    {
                        tree.children.map(item => {
                            return (
                                <Menu.Item
                                    icon={
                                        React.createElement(
                                            Icon[item.icon]
                                        )
                                    }
                                    key={item.title}
                                    title={item.title}
                                >
                                    <Link to={"/menu" + item.url}>{item.title}</Link>
                                </Menu.Item>
                            )
                        })
                    }
                </SubMenu>
            )
        });
        const {location = {} } = this.props;
        const {state } = location;
        return (
            <Layout style={{minHeight: '100vh'}}>
                <Sider collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
                    <Menu theme="dark"
                          defaultSelectedKeys={['1']}
                          mode="inline"

                    >
                        {menuItemList}
                    </Menu>
                </Sider>
                <Layout className="site-layout">
                    <PageHeader
                        className="site-page-header"
                        backIcon={<GithubOutlined/>}
                        onBack={() => null}
                        title={"欢迎您：" + state}
                        extra={
                            [
                                <Avatar icon={<GithubOutlined/>}/>,
                                <Select
                                    style={{width: 60}}
                                    bordered={false}
                                    onSelect={value => {
                                        this.loginOut(value)
                                    }}
                                >
                                    <Option value="loginOut">退出</Option>
                                </Select>]
                        }
                    />
                    <Content style={{margin: '16px 16px'}}>
                        <Switch>
                            {
                                routers.map(item => {
                                    return (
                                        <Route key={item.title} path={"/menu" + item.path} component={item.component}/>
                                    )
                                })
                            }
                        </Switch>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default withRouter(LeftMenu);