import React from 'react'
import ContentNav from '../components/ContentNav'
import {AccountDetail, AccountList, AccountSearch} from './'
import {fetch} from '../../actions/account/account.list';
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'

import './account.css'

class Account extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selected: {}
        }
    }

    handleChangeSearchFilters = (condition) => {
        console.debug('search condition: %s', JSON.stringify(condition));

        this.setState(condition, () => {
            this.props.fetch(this.state)
        });
    };

    handleClickAccount = (selectedAccount) => {
        console.debug('selected account: %s ', JSON.stringify(selectedAccount));
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
                            <AccountDetail/>
                        </div>
                    </div>

                </section>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({});
const mapDispatchToProps = (dispatch) => bindActionCreators({fetch}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Account)
