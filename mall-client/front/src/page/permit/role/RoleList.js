import React, {Component} from "react";
import {Button, Form, Input, Modal, PageHeader, Space, Switch, Table} from "antd";
import {SearchOutlined, UnorderedListOutlined} from '@ant-design/icons';
import HttpUtil from "../../../utils/HttpUtil";
import Api from "../../../common/Api";
import "./permit.scss"

class RoleList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            columns: [
                {
                    align: "center",
                    title: '角色名称',
                    dataIndex: 'name',
                },
                {
                    align: "center",
                    title: '描述',
                    dataIndex: 'description',
                    width: 220,
                },
                {
                    align: "center",
                    title: '用户数',
                    dataIndex: 'adminCount',
                    width: 120,
                },
                {
                    align: "center",
                    title: '添加时间',
                    dataIndex: 'createTime',
                },
                {
                    align: "center",
                    title: '是否启用',
                    dataIndex: 'status',
                    width: 120,
                    render: (text, record, index) => {
                        return (
                            <Switch defaultChecked onChange={val => {
                                this.switchChange(val)
                            }}/>
                        );
                    }
                },
                {
                    align: "center",
                    title: '操作',
                    dataIndex: 'operator',
                    key: 'x',
                    render: (text, record, index) =>

                        <Space
                            direction={"vertical"}
                            size={0}
                        >
                            <div>
                                <Button type="link">
                                    分配菜单
                                </Button>
                                <Button type="link">
                                    分配资源
                                </Button>
                            </div>
                            <div>
                                <Button type="link">
                                    编辑
                                </Button>
                                <Button type="link">
                                    删除
                                </Button>
                            </div>
                        </Space>

                },
            ],
            dataSource: [],
            roleName: "",

        }
    }

    componentWillMount() {
        HttpUtil.get(Api.findRoleList, this.props).then(response => {
            if (response.status !== "200") {
                Modal.error({
                    title: '系统出错',
                    content: '暂无数据请联系管理员'
                });
                return;
            }
            this.setState({
                dataSource: response.data
            })
        })
    }


    switchChange = (checked) => {
        console.log(`switch to ${checked}`);
    };

    render() {
        const {columns, dataSource, roleName} = this.state;
        return (
            <div className="permit">
                <PageHeader
                    ghost={false}
                    onBack={() => {
                    }}
                    backIcon={<SearchOutlined/>}
                    title="筛选搜索"
                    extra={[
                        <Button
                            shape={"round"}
                            type="primary"
                            size={"small"}
                        >
                            查询搜索
                        </Button>,
                        <Button
                            shape={"round"}
                            size={"small"}
                            onClick={() => {
                                this.setState({
                                    roleName: ""
                                })
                            }}
                        >
                            重置
                        </Button>,
                    ]}
                >
                    <Form>
                        <Form.Item
                            label="输入搜索"
                            labelCol={{span: 3}}
                        >
                            <Input
                                placeholder="角色名称"
                                style={{width: 175}}
                                value={roleName}
                                onChange={e => {
                                    this.setState({
                                        roleName: e.target.value
                                    })
                                }}
                            />
                        </Form.Item>
                    </Form>
                </PageHeader>
                <PageHeader
                    ghost={false}
                    onBack={() => {
                    }}
                    backIcon={<UnorderedListOutlined/>}
                    title="数据列表"
                    extra={[
                        <Button
                            shape={"round"}
                            size={"small"}
                        >
                            添加
                        </Button>,
                    ]}
                />
                <Table
                    columns={columns}
                    dataSource={dataSource}
                />
            </div>
        );
    }
}

export default RoleList;