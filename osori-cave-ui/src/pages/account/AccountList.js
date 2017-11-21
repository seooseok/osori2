import React from 'react'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'
import {findOne} from "../../actions/account/account.detail";
import {AccountDetail} from './'


import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css'


class AccountList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            accounts: []
        };
    }

    componentWillReceiveProps(nextProps) {
        console.debug("componentWillReceiveProps: " + JSON.stringify(nextProps));
        if (nextProps.payload !== undefined) {
            this.setState({
                accounts: nextProps.payload.content
            });
        }
    }

    onRowSelect = (row, isSelected, e) => {
        if (isSelected) {
            console.debug('selected account: %s ', JSON.stringify(row));

            let url = row.links.find(item => {
                return item.rel === 'detail'
            }).href;

            if (url === undefined)
                console.err('can\'t find detail url. links: ', JSON.stringify(row.links));

            this.props.findOne(url)
        }
    };

    onChangeAccountDetail = (formData) => {
        console.debug('change account detail: %s', JSON.stringify(formData));
        let targetRow = this.state.accounts.find(row => {
            return row.id === formData.id
        });
        targetRow.name = formData.name;
        targetRow.status = formData.status;

        this.setState({
            accounts: this.state.accounts.map((row) => row.id === formData.id ? targetRow : row)
        })
    };

    onExpireAccountDetail = (id) => {
        console.debug('expired account detail: %s', id);
        this.setState({
            accounts: this.state.accounts.filter((row) => row.id !== id)
        })
    };

    render() {

        const options = {
            noDataText: 'No User Account',
            sizePerPage: 15,
            sizePerPageList: [
                {
                    text: '15', value: 15
                },
                {
                    text: '30', value: 30
                },
                {
                    text: 'All', value: this.state.accounts.length
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
            <div className="row">
                <div className="col-md-9">
                    <div className="box box-success">
                        <div className="box-header with-border">
                            <h5 className="box-title">Search Result</h5>
                        </div>
                        <div className="box-body">
                            <BootstrapTable data={this.state.accounts} options={options} pagination
                                            selectRow={selectRowProp}>
                                <TableHeaderColumn dataField='id' isKey hidden>ID</TableHeaderColumn>
                                <TableHeaderColumn dataField='loginId' dataSort={true}>Login ID</TableHeaderColumn>
                                <TableHeaderColumn dataField='name' dataSort={true}>Name</TableHeaderColumn>
                                <TableHeaderColumn dataField='created' dataSort={true}>Creation date</TableHeaderColumn>
                                <TableHeaderColumn dataField='status' dataSort={true}>Status</TableHeaderColumn>
                            </BootstrapTable>
                        </div>
                        {
                            this.props.isFetching &&
                            <div className="overlay">
                                <i className="fa fa-refresh fa-spin"></i>
                            </div>
                        }
                    </div>
                </div>
                <div className="col-md-3">
                    <AccountDetail onChangeAccountDetail={this.onChangeAccountDetail}
                                   onExpireAccountDetail={this.onExpireAccountDetail}/>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        isFetching: state.accountList.isFetching,
        payload: state.accountList.payload
    };
};

const mapDispatchToProps = (dispatch) => bindActionCreators({findOne}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(AccountList)

