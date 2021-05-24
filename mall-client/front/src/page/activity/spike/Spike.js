import React, {Component} from "react";
import {Button, Form, Input, Modal, PageHeader, Switch, Table,Pagination} from "antd";
import {SearchOutlined,UnorderedListOutlined } from '@ant-design/icons';
import HttpUtil from "../../../utils/HttpUtil";
import Api from "../../../common/Api";
import "./Spike.scss"

class Spike extends Component {
    constructor(props) {
        super(props);
        this.state = {
            columns: [
                {
                    align: "center",
                    title: '活动标题',
                    dataIndex: 'name',
                },
                {
                    align: "center",
                    title: '活动状态',
                    dataIndex: 'description',
                    width: 220,
                },
                {
                    align: "center",
                    title: '开始时间',
                    dataIndex: 'adminCount',
                    width: 120,
                },
                {
                    align: "center",
                    title: '结束时间',
                    dataIndex: 'createTime',
                },
                {
                    align: "center",
                    title: '上线/下线',
                    dataIndex: 'status',
                    width: 120,
                    render: (text, record, index) => {
                        return(
                            <Switch defaultChecked onChange={val=>{this.switchChange(val)}} />
                        );
                    }
                },
                {
                    align: "center",
                    title: '操作',
                    dataIndex: 'operator',
                    key: 'x',
                    render: (text, record, index) => <a
                        onClick={() => this.downloadSingleFile(text, record, index)}>下载</a>,
                },
            ],
            dataSource: [],
        }
    }

    componentWillMount() {
        // HttpUtil.get(Api.findRoleList,this.props).then(response => {
        //     if (response.status !== "200") {
        //         Modal.error({
        //             title: '系统出错',
        //             content: '暂无数据请联系管理员'
        //         });
        //         return;
        //     }
        //     this.setState({
        //         dataSource: response.data
        //     })
        // })
    }

    render() {
        const {columns,dataSource} = this.state;
        return(
            <div className="spike">
                <PageHeader
                    ghost={false}
                    onBack={() => {
                    }}
                    backIcon={<SearchOutlined />}
                    title="筛选搜索"
                    extra={[
                        <Button
                            shape={"round"}
                        >
                            重置
                        </Button>,
                        <Button
                            shape={"round"}
                            type="primary"
                        >
                            查询搜索
                        </Button>,
                    ]}
                >
                    <Form>
                        <Form.Item
                            label="输入搜索"
                            name="username"
                            labelCol={{span: 3}}
                        >
                            <Input  placeholder="输入角色名称" style={{width:175}}/>
                        </Form.Item>
                    </Form>
                </PageHeader>
                <PageHeader
                    ghost={false}
                    onBack={() => {
                    }}
                    backIcon={<UnorderedListOutlined />}
                    title="数据列表"
                    extra={[
                        <Button
                            shape={"round"}
                        >
                            秒杀时间段列表
                        </Button>,
                        <Button
                            shape={"round"}
                        >
                            添加活动
                        </Button>,
                    ]}
                />

                <Table
                    columns={columns}
                    dataSource={dataSource}
                />

            </div>
        )
    }

}
export default Spike;