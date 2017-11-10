import React from 'react'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table'

import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css'


class AccountList extends React.Component {

    render() {
        let data = this.props.data;
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
                    text: 'All', value: data.length
                }
            ]
        };

        const selectRowProp = {
            mode: 'radio',
            bgColor: '#ddd',
            hideSelectColumn: true,
            clickToSelect: true,
            onSelect: onRowSelect
        };

        function onRowSelect(row, isSelected, e) {
            let rowStr = '';
            for (const prop in row) {
                rowStr += prop + ': "' + row[prop] + '"';
            }
            console.debug(`is selected: ${isSelected}, ${rowStr}`);
        }

        return (
            <div className="box">
                <div className="box-header with-border">
                    <h5 className="box-title">Search Result</h5>
                </div>
                <div className="box-body">
                    <BootstrapTable data={data} options={options} pagination selectRow={selectRowProp}>
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

export default AccountList


AccountList.defaultProps = {
    data: [
        {
            id: 1,
            loginId: '5dolstory',
            name: 'seo o seok',
            created: '2017-07-24',
            status: 'allow'
        },
        {
            id: 2,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 3,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 4,
            loginId: 'test1',
            name: 'elijah1',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 5,
            loginId: 'test2',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 6,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 7,
            loginId: 'test8',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 8,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 9,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 10,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 11,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 12,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 13,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 1,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 14,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        },
        {
            id: 15,
            loginId: 'elijah17',
            name: 'elijah',
            created: '2016-07-24',
            status: 'reject'
        }
    ]
};
