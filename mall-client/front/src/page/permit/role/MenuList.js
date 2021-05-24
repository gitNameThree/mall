import React, {Component} from "react";
import {Button, Modal, PageHeader} from "antd";
import {MenuOutlined} from '@ant-design/icons'
import HttpUtil from "../../../utils/HttpUtil";
import Api from "../../../common/Api";
import CustomTable from "../../base/Table";
class MenuList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            columns: [
                {
                    align: "center",
                    title: '菜单名称',
                    dataIndex: 'title',
                },
                {
                    align: "center",
                    title: '前端名称',
                    dataIndex: 'name',
                },
                {
                    align: "center",
                    title: '前端图标',
                    dataIndex: 'icon',
                    width: 120,
                },
                {
                    align: "center",
                    title: '是否显示',
                    dataIndex: 'hidden',
                    width: 120,
                    render: (text, record, index) => {
                        return(
                            <a onClick={() => this.downloadSingleFile(text, record, index)}>下载</a>
                        );
                    }
                },
                {
                    align: "center",
                    title: '排序',
                    dataIndex: 'sort',
                },
                {
                    align: "center",
                    title: '设置',
                    dataIndex: 'title',
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

    }

    render() {
        const {columns, dataSource} = this.state;
        return (
            <div>
                <PageHeader
                    ghost={false}
                    onBack={() => {
                    }}
                    backIcon={<MenuOutlined/>}
                    extra={[
                        <Button
                            key="3"
                            shape={"round"}
                        >
                            添加
                        </Button>,
                    ]}
                />
                <CustomTable
                    columns={columns}
                    dataSource={dataSource}
                />
            </div>

        );
    }
}

export default MenuList;