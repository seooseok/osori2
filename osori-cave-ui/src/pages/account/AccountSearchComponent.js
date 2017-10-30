import React from 'react'
import IconInput from '../../components/input/IconInput'
import DateInput from '../../components/input/DateInput'

class AccountSearchComponent extends React.Component {
    render() {
        return (
            <div className="box box-info">
                <div className="box-header with-border">
                    <h3 className="box-title">Search </h3>
                </div>
                <div className="box-body">
                    <div className="row">
                        <div className="col-md-2">
                            <IconInput icon="fa-laptop" name="loginId" holder="Login ID"/>
                        </div>
                        <div className="col-md-2">
                            <IconInput icon="fa-user" name="name" holder="Name"/>
                        </div>
                        <div className="col-md-3">
                            <DateInput/>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default AccountSearchComponent
