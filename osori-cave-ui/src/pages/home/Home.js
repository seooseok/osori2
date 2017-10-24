import React from 'react'

import './home.css'

class Home extends React.Component {
    render() {
        return (
            <div className="panel panel-flat">
                <div className="panel-heading">
                    <h5 className="panel-title">Osori Home</h5>
                    <div className="heading-elements">
                        <ul className="icons-list">
                            <li>
                                <a data-action="collapse"/>
                            </li>
                            <li>
                                <a data-action="close"/>
                            </li>
                        </ul>
                    </div>
                </div>

                <div className="panel-body">
                    <p>Welcome to Osori</p>
                </div>
            </div>
        )
    }
}

export default Home
