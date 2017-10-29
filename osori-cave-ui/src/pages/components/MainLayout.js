import React from 'react'
import {Route} from 'react-router-dom'
import {MainNav, TopNav} from './'
import ContentNav from "./ContentNav";

const MainLayout = ({component: Component, ...rest}) => {
    return (
        <Route
            {...rest}
            render={matchProps =>
                <div className="hold-transition skin-blue sidebar-mini">
                    <div className="wrapper">
                        {/*Top Navigation*/}
                        <header className="main-header">
                            <div>
                                {/* Logo */}
                                <div>
                                    <a href="/" className="logo">
                                        <span className="logo-mini"><b>O</b>sori</span>
                                        <span className="logo-lg"><b>Osori</b>Cave</span>
                                    </a>
                                </div>

                                {/* Header navigation bar */}
                                <TopNav/>
                            </div>
                        </header>

                        {/*Left Side Main Navigation*/}
                        <aside className="main-sidebar">
                            <section className="sidebar">
                                <MainNav/>
                            </section>
                        </aside>

                        {/* Main content */}
                        <div className="content-wrapper">
                            <ContentNav/>
                            <Component {...matchProps} />
                        </div>

                        {/* Footer */}
                        <footer className="main-footer">
                            <div className="pull-right hidden-xs">
                                <b>Version</b> 1.0.0
                            </div>
                            <strong>Copyright &copy; 2016-2017 <a
                                href="https://github.com/seooseok">5dolstory</a>.</strong> All rights
                            reserved.
                        </footer>
                        <aside className="control-sidebar control-sidebar-dark">
                            {/* Control Sidebar */}
                        </aside>
                        <div className="control-sidebar-bg"></div>
                    </div>
                </div>
            }
        />
    )
}

export default MainLayout
