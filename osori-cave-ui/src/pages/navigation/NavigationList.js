import React from 'react'
import {connect} from 'react-redux'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table'

class NavigationList extends React.Component {

    render() {
        const options = {
            noDataText: 'Not found',
            sizePerPage: 16,
            sizePerPageList: [
                {
                    text: '15', value: 15
                },
                {
                    text: '30', value: 30
                }
            ]
        };

        return (
            <div className="box box-default">
                <div className="box-header with-border">
                    <h5 className="box-title">Api List</h5>
                </div>
                <div className="box-body">
                    <BootstrapTable data={this.props.navigations} options={options} pagination search>
                        <TableHeaderColumn dataField='id' isKey hidden>ID</TableHeaderColumn>
                        <TableHeaderColumn width='30%' dataField='name' dataSort={true}>Name</TableHeaderColumn>
                        <TableHeaderColumn dataField='fullUri' tdStyle={{whiteSpace: 'normal'}}
                                           dataSort={true}>URI</TableHeaderColumn>
                      <TableHeaderColumn dataField="methodType">Method</TableHeaderColumn>
                    </BootstrapTable>
                </div>
                {
                    this.props.isFetching &&
                    <div className="overlay">
                        <i className="fa fa-refresh fa-spin"></i>
                    </div>
                }
            </div>
        )
    }

}

const mapStateToProps = (state) => {
    let navigations = [];
    if (state.navigationList.payload !== undefined)
        navigations = state.navigationList.payload.content
    return {
        isFetching: state.navigationList.isFetching,
        navigations
    }
};

export default connect(mapStateToProps, null)(NavigationList)
