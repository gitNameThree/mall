import React, {Component} from 'react';

import {Button, Form, Input, message} from 'antd';
import {LockOutlined, UserOutlined} from '@ant-design/icons';
import "./login.scss"
import HttpUtil from "../../utils/HttpUtil";
import Api from "../../common/Api";
import loginImg from "../../static/img/login_center_bg.png"
import cookie from 'react-cookies'
import {withRouter} from "react-router-dom";
import Constant from "../../common/Constant";


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: ""
        }
    }

    login = () => {
        const {username, password} = this.state;
        const loginData={
            username,
            password
        };
        HttpUtil.post(Api.login,loginData).then(response => {

            if (response.status !== "200") {
                message.error(response.message);
                return;
            }
            if(!response.data.accessToken){
                message.error("登录失败，请联系管理员");
                return;
            }
            // 把用户信息存到cookie //一天
            let timeout = new Date(new Date().getTime() + response.data.tokenTimeout * 1000);
            cookie.save(Constant.COOKIE_LOGIN_KEY,response.data.accessToken,{ expires: timeout });
            cookie.save(Constant.COOKIE_LOGIN_USERNAME,response.data.username,{ expires: timeout });
            this.props.history.push("/menu/home",response.data.username);
        })
    };

    render() {
        return (
            <div id={"login"}>
                <div className="shadow">
                    <div className="spaceLogin">
                        <Form
                            name="normal_login"
                            className="login-form"
                            initialValues={{ remember: true }}
                        >
                            <Form.Item
                                name="username"
                                rules={[{ required: true, message: 'Please input your Username!' }]}
                            >
                                <Input
                                    prefix={<UserOutlined className="site-form-item-icon" />}
                                    placeholder="Username"
                                    onChange={e=>
                                        this.setState({
                                            username: e.target.value
                                        })
                                    }
                                />
                            </Form.Item>
                            <Form.Item
                                name="password"
                                rules={[{ required: true, message: 'Please input your Password!' }]}
                            >
                                <Input
                                    prefix={<LockOutlined className="site-form-item-icon" />}
                                    type="password"
                                    placeholder="Password"
                                    onChange={e=>
                                        this.setState({
                                            password: e.target.value
                                        })
                                    }
                                />
                            </Form.Item>
                            <Form.Item>
                                <Button
                                    type="primary"
                                    htmlType="submit"
                                    className="login-form-button"
                                    onClick={()=>{this.login()}}
                                >
                                    login in
                                </Button>
                            </Form.Item>
                        </Form>
                    </div>
                </div>
                <img src={loginImg} className="loginImg"/>
            </div>

        )
    }

}

export default withRouter(Login);