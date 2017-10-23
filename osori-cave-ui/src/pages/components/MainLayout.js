import React from 'react'
import {Route} from 'react-router-dom'
import {MainNav, TopNav} from './'

const MainLayout = ({component: Component, ...rest}) => {
    return (
        <Route
            {...rest}
            render={matchProps =>
                <div className="navbar-bottom">
                    <TopNav/>
                    {/* Page container */}
                    <div className="page-container">
                        {/* Page content */}
                        <div className="page-content">
                            {/* Main sidebar */}
                            <div className="sidebar sidebar-main sidebar-default">
                                <MainNav/>
                            </div>
                            {/* /main sidebar */}

                            {/* Main content */}
                            <div className="content-wrapper">
                                <Component {...matchProps} />
                            </div>
                        </div>
                    </div>
                </div>}
        />
    )
}

export default MainLayout
