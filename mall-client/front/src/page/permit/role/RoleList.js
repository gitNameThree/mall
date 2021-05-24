import React, {Component} from "react";
import {Button, Form, Input, Modal, PageHeader, Radio, Space, Switch, Table} from "antd";
import {ExclamationCircleOutlined, SearchOutlined, UnorderedListOutlined} from '@ant-design/icons';
import HttpUtil from "../../../utils/HttpUtil";
import Api from "../../../common/Api";
import "./permit.scss"
import Constant from "../../../common/Constant";

const {TextArea} = Input;
const {confirm} = Modal;

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
                    render: (text, data, index) => {
                        return (
                            <Switch
                                checked={data.status == Constant.IS_STATUS}
                                onChange={status => {
                                    this.switchChange(data.id, status)
                                }}/>
                        );
                    }
                },
                {
                    align: "center",
                    title: '操作',
                    dataIndex: 'operator',
                    key: 'x',
                    render: (text, data, index) =>

                        <Space
                            direction={"vertical"}
                            size={0}
                        >
                            <div>
                                <Button type="link">
                                    分配菜单
                                </Button>
                            </div>
                            <div>
                                <Button
                                    type="link"
                                    onClick={() => {
                                        this.editRole(data.id)
                                    }}
                                >
                                    编辑
                                </Button>
                                <Button
                                    type="link"
                                    onClick={() => {
                                        this.deleteRole(data.id, props)
                                    }}
                                >
                                    删除
                                </Button>
                            </div>
                        </Space>

                },
            ],
            dataSource: [],
            name: "",
            roleName: '',
            description: '',
            current: 1,
            pageSize: 10,
            total: 0,
            status: 0,
            id: null,
            isModalVisible: false,

        }
    }

    componentWillMount() {
        // 初始化查询列表
        this.onSearch();
    }

    /**
     * 是否启用改变的行数
     * @param checked
     * @param roleId
     */
    switchChange = (roleId, checked) => {
        const changData = {
            id: roleId,
            status: checked ? 1 : 0
        };
        HttpUtil.post(Api.changeStatus, changData, this.props).then(response => {
            if (response.status != Constant.SUCCESS_CODE || !response.data) {
                Modal.error({
                    title: '',
                    content: '操作失败，请联系管理员'
                });
                return;
            }
        });
        this.setState({}, () => {
            this.onSearch();
        })

    };

    /**
     * 编辑角色
     * @param id 角色ID
     */
    editRole = (id) => {
        HttpUtil.get(Api.getRole + id, this.props).then(response => {
            if (response.status !== "200") {
                Modal.error({
                    title: '系统出错',
                    content: '暂无数据请联系管理员'
                });
                return;
            }
            const {id, name, description, status} = response.data;
            this.setState({
                id,
                name,
                description,
                status,
                isModalVisible: true
            })
        })

    };

    deleteRole = (id, props) => {
        confirm({
            title: '确认要删除角色?',
            icon: <ExclamationCircleOutlined/>,
            content: '',
            onOk() {
                HttpUtil.get(Api.deleteRole + id, props).then(response => {
                    if (response.status !== Constant.SUCCESS_CODE) {
                        Modal.error({
                            title: '系统出错',
                            content: '请联系管理员'
                        });
                        return;
                    }
                    Modal.success({
                        title: '删除成功',
                        content: '删除成功'
                    });


                });

            },
            onCancel() {
                console.log('Cancel');
            },
        });
    };
    /**
     * 角色的新增和编辑操作
     */
    operateRole = () => {
        const {id, name, description, status} = this.state;
        if (!name || name == '') {
            Modal.error({
                title: '操作失败',
                content: '角色名称必填'
            });
            return;
        }

        const addRoleParams = {
            id,
            name,
            description,
            status
        };
        HttpUtil.post(Api.editRole, addRoleParams, this.props).then(response => {
            if (response.status != Constant.SUCCESS_CODE) {
                Modal.error({
                    title: '',
                    content: '操作失败，请联系管理员'
                });
                return;
            }
            this.setState({
                isModalVisible: false
            }, () => {
                this.onSearch();
                this.clearRoleInfo();
            });
            Modal.success({
                title: '',
                content: '操作成功'
            });
        })

    };

    /**
     * 清除角色的缓存信息
     * 解决的问题：编辑之后再新增，弹窗有原来觉得信息
     */
    clearRoleInfo = () => {
        this.setState({
            id: null,
            name: '',
            description: '',
            status: '',

        })

    };

    /**
     * 分页查询角色列表
     */
    onSearch = () => {
        const {current, pageSize, roleName} = this.state;
        const roleSearch = {
            current,
            pageSize,
            name: roleName
        };
        HttpUtil.post(Api.findRoleList, roleSearch, this.props).then(response => {
            if (response.status !== "200") {
                Modal.error({
                    title: '系统出错',
                    content: '暂无数据请联系管理员'
                });
                return;
            }
            const {total, records} = response.data;
            this.setState({
                total: total,
                dataSource: records
            })
        })


    };

    render() {
        const {
            columns,
            dataSource,
            name,
            roleName,
            current,
            pageSize,
            total,
            isModalVisible,
            status = 0,
            description
        } = this.state;
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
                            onClick={() => {
                                this.onSearch()
                            }}
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
                            onClick={() => {
                                this.setState({
                                    isModalVisible: true
                                })
                            }}
                        >
                            添加
                        </Button>,
                    ]}
                />
                <Table
                    pagination={{
                        defaultCurrent: 1,
                        defaultPageSize: 10,
                        total,
                        current,
                        pageSize,
                        pageSizeOptions: [10, 20, 50, 100],
                        onChange: (current, pageSize) => {
                            this.setState({
                                current,
                                pageSize
                            })
                        }
                    }}
                    columns={columns}
                    dataSource={dataSource}
                />
                <div className={"model"}>
                    <Modal title="添加角色"
                           visible={isModalVisible}
                           cancelText="取消"
                           okText="确定"
                           onCancel={() => {
                               this.setState({
                                   isModalVisible: false
                               }, () => {
                                   this.clearRoleInfo();
                               })
                           }}
                           onOk={() => {
                               this.operateRole();
                           }}
                    >
                        <Form>

                            <Form.Item
                                label="角色名称:"
                                labelCol={{span: 3, offset: 3}}
                                colon={true}
                            >
                                <Input
                                    style={{width: 262}}
                                    value={name}
                                    onChange={e => {
                                        this.setState({
                                            name: e.target.value
                                        })
                                    }}
                                />
                            </Form.Item>
                            <Form.Item
                                label="描述:"
                                labelCol={{span: 3, offset: 3}}
                            >
                                <TextArea
                                    autoSize={{minRows: 4, maxRows: 6}}
                                    style={{width: 262}}
                                    value={description}
                                    onChange={e => {
                                        this.setState({
                                            description: e.target.value
                                        })
                                    }}
                                />
                            </Form.Item>
                            <Form.Item
                                label="是否启用:"
                                labelCol={{span: 3, offset: 3}}
                                colon={true}
                            >
                                <Radio.Group
                                    options={[{label: '是', value: 1}, {label: '否', value: 0}]}
                                    onChange={e => {
                                        this.setState({
                                            status: e.target.value
                                        })
                                    }}
                                    value={status}
                                />
                            </Form.Item>
                        </Form>
                    </Modal>
                </div>
            </div>
        );
    }
}

export default RoleList;