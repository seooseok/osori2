import React from 'react'

class TopNav extends React.Component {

    pushMenu() {
        var body = document.getElementById('root').childNodes[0]

        if (body.clientWidth > 768) {
            if (body.className.indexOf('sidebar-collapse') === -1) {
                body.className += ' sidebar-collapse';
            } else {
                body.className = body.className.replace(' sidebar-collapse', '');
            }
        } else {
            if (body.className.indexOf('sidebar-open') === -1) {
                body.className += ' sidebar-open';
            } else {
                body.className = body.className.replace(' sidebar-open', '');
            }
        }
    }

    render() {
        return (
            <div>
                <nav className="navbar navbar-static-top">
                    {/* Sidebar toggle button */}
                    <a href="#" className="sidebar-toggle" data-toggle="offcanvas" role="button"
                       onClick={this.pushMenu}>
                        <span className="sr-only">Toggle navigation</span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                    </a>

                    {/* Top navigation menu */}
                    <div className="navbar-custom-menu">
                        <ul className="nav navbar-nav">

                            {/* Notifications */}
                            <li className="dropdown notifications-menu">
                                <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                                    <i className="fa fa-bell-o"></i>
                                    <span className="label label-warning">10</span>
                                </a>
                                <ul className="dropdown-menu">
                                    <li className="header">You have 10 notifications</li>
                                    <li>
                                        <ul className="menu">
                                            <li>
                                                <a href="#">
                                                    <i className="fa fa-users text-aqua"></i> 5 new members joined
                                                    today
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li className="footer"><a href="#">View all</a></li>
                                </ul>
                            </li>

                            {/* Sign out */}
                            <li>
                                <a href="/signout">SignOut</a>
                            </li>

                            {/* Control Sidebar Toggle Button */}
                            <li>
                                <a href="#" data-toggle="control-sidebar"><i className="fa fa-gears"></i></a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        )
    }
}

export default TopNav
