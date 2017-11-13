import React from 'react'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table'
import {connect} from 'react-redux'

import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css'


class AccountList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selected: {}
        }
    }

    onRowSelect = (row, isSelected, e) => {
        console.log(e)
        this.state.selected = row
        this.props.onClickAccount(this.state)
    };

    render() {
        let accounts = [];

        if (this.props.payload !== undefined && this.props.payload !== '') {
            accounts = this.props.payload.content;
        }

        const options = {
            noDataText: 'No User Account',
            sizePerPage: 10,
            sizePerPageList: [
                {
                    text: '10', value: 10
                },
                {
                    text: '30', value: 30
                },
                {
                    text: 'All', value: accounts.length
                }
            ]
        };

        const selectRowProp = {
            mode: 'radio',
            bgColor: '#ddd',
            hideSelectColumn: true,
            clickToSelect: true,
            onSelect: this.onRowSelect
        };


        return (
            <div className="box">
                <div className="box-header with-border">
                    <h5 className="box-title">Search Result</h5>
                </div>
                <div className="box-body">
                    <BootstrapTable data={accounts} options={options} pagination selectRow={selectRowProp}>
                        <TableHeaderColumn dataField='id' isKey hidden>ID</TableHeaderColumn>
                        <TableHeaderColumn dataField='loginId' dataSort={true}>Login ID</TableHeaderColumn>
                        <TableHeaderColumn dataField='name' dataSort={true}>Name</TableHeaderColumn>
                        <TableHeaderColumn dataField='created' dataSort={true}>Creation date</TableHeaderColumn>
                        <TableHeaderColumn dataField='status' dataSort={true}>Status</TableHeaderColumn>
                    </BootstrapTable>
                </div>
            </div>
        )
    }
}

let mapStateToProps = (state) => {
    return {
        payload: state.accountList.payload
    };
};

export default connect(mapStateToProps, null)(AccountList)

