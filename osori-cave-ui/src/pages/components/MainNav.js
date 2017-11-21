import React, {Component} from 'react'
import {Link} from 'react-router-dom'


class MainNav extends Component {
    render() {
        return (
            <div>
                {/* Search form */}
                <form action="#" method="get" className="sidebar-form">
                    <div className="input-group">
                        <input type="text" name="q" className="form-control" placeholder="Search..."></input>
                        <span className="input-group-btn">
                            <button type="submit" name="search" id="search-btn" className="btn btn-flat"><i
                                className="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </form>

                {/* Sidebar Menu*/}
                <ul className="sidebar-menu" data-widget="tree">
                    <li className="header">Users</li>
                    <li><Link to="/account"><i className="fa fa-users"></i><span>Accounts</span></Link></li>
                    <li><Link to="/privilege"><i className="fa fa-wrench"></i><span>Privilege</span></Link></li>
                    <li className="header">Permission</li>

                    <li className="header">Navigation</li>
                    <li><Link to="/navigation"><i className="fa fa-link"></i><span>Navigation</span></Link></li>
                    <li className="header">Administrator</li>
                    <li><a href="#"><i className="fa fa-circle-o text-aqua"></i> <span>Configuration</span></a></li>
                    <li><a href="#"><i className="fa fa-circle-o text-red"></i> <span>System</span></a></li>
                </ul>
            </div>
        )
    }
}

export default MainNav
