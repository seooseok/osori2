import React from 'react'
import ContentNav from '../components/ContentNav'
import {AccountDetail, AccountList, AccountSearch} from './'
import {findAll} from '../../actions/account/account.list';
import {findOne} from "../../actions/account/account.detail";
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'

import './account.css'

class Account extends React.Component {

    handleChangeSearchFilters = (condition) => {
        console.debug('search condition: %s', JSON.stringify(condition));

        this.setState(condition, () => {
            this.props.findAll(this.state)
        });
    };

    handleClickAccount = (selectedAccount) => {
        console.debug('selected account: %s ', JSON.stringify(selectedAccount));

        let url = selectedAccount.links.find(item => {
            return item.rel === 'detail'
        }).href;

        if (url === undefined)
            console.err('can\'t find detail url. links: ', JSON.stringify(selectedAccount.links));

        this.props.findOne(url)
    };

    render() {
        return (
            <div>
                <ContentNav category="Users" name="Account" description="Set personal information for the user"/>
                <section className="content">
                    <AccountSearch onChangeSearchFilters={this.handleChangeSearchFilters}/>
                    <div className="row">
                        <div className="col-md-9">
                            <AccountList onClickAccount={this.handleClickAccount}/>
                        </div>
                        <div className={"col-md-3"}>
                            <AccountDetail ref="accountDetail"/>
                        </div>
                    </div>

                </section>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({});
const mapDispatchToProps = (dispatch) => bindActionCreators({findAll, findOne}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Account)
