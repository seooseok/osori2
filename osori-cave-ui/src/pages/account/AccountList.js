import React from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table'
import {AccountDetail} from './'
import {findOne} from "../../actions/account/account.detail";

import * as hateoas from "../../util/HateoasUtil";

class AccountList extends React.Component {

    onRowSelect = (row, isSelected, e) => {
        if (isSelected) {
            console.debug('selected account: %s ', JSON.stringify(row));

            let url = hateoas.findUrl(row, 'detail');
            this.props.findOne(url)
        }
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
                    text: 'All', value: this.props.accounts.length
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
                            <BootstrapTable data={this.props.accounts} options={options} pagination
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
                    <AccountDetail onChangeAccountDetail={this.onChangeAccountDetail}/>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    let accounts = [];
    if (state.accountList.payload !== undefined)
        accounts = state.accountList.payload.content;

    return {
        isFetching: state.accountList.isFetching,
        accounts
    };
};

const mapDispatchToProps = (dispatch) => bindActionCreators({findOne}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(AccountList)

