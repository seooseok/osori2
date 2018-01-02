import React from 'react'
import ContentNav from '../components/ContentNav'
import {AccountList, AccountSearch} from './'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import {findAll} from '../../actions/account/account.list'

import './account.css'

class Account extends React.Component {

    handleChangeSearchFilters = (condition) => {
        console.debug('search condition: %s', JSON.stringify(condition));

        this.setState(condition, () => {
            this.props.findAll(this.state)
        });
    };

    render() {
        return (
            <div>
                <ContentNav category="Users" name="Account" description="Set personal information for the user"/>
                <section className="content">
                    <AccountSearch onChangeSearchFilters={this.handleChangeSearchFilters}/>
                    <AccountList/>
                </section>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({});
const mapDispatchToProps = (dispatch) => bindActionCreators({findAll}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Account)
