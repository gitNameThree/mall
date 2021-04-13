import React, {Component} from "react";
import {Table} from "antd";

class CustomTable extends Component {
    constructor(props) {
        super(props);

    }

    componentWillMount() {
    }

    render() {
        const {columns, dataSource} = this.props;
        return (
            <Table style={{marginTop: '20px'}}
                // pagination={{
                //     total: 100,
                //     current: current,
                //     hideOnSinglePage: false,
                //     pageSizeOptions: [10, 20, 50, 100, 500, 1000, 2000],
                //     onChange: (page, pageSize) => {
                //         // 改变大小的时候也会调这个方法
                //         if (!page) {
                //             return;
                //         }
                //         this.setState({
                //             current: page
                //         }, () => {
                //             this.onSearch();
                //         });
                //
                //     },
                //     onShowSizeChange: (currents, size) => {
                //         this.setState({
                //             size: size
                //         }, () => {
                //             this.onSearch()
                //         });
                //
                //     }
                // }}
                   columns={columns}
                   dataSource={dataSource}
                   bordered
                   tableLayout={"auto"}
            />
        );
    }
}

export default CustomTable;