
import React, { useEffect, useState } from "react";
import { NavLink, Route, Routes, Navigate, useLocation } from "react-router-dom";
import { pages } from "./config";
import CrudPage from "./components/CrudPage";
import Dashboard from "./components/Dashboard";

const navGroups = [
  { id: "dashboard", title: "Main", items: [{ title: "Dashboard", path: "/" }, { title: "AI Assistant", path: "/assistant" }] },
  { id: "wildlife", title: "Wildlife Service", items: [{ title: "Animals", path: "/animals" }, { title: "Species", path: "/species" }] },
  { id: "operations", title: "Operations Service", items: [{ title: "Patrols", path: "/patrols" }, { title: "Incidents", path: "/incidents" }, { title: "Rescues", path: "/rescues" }, { title: "Staff", path: "/staff" }, { title: "Resources", path: "/resources" }] },
  { id: "conservation", title: "Conservation Service", items: [{ title: "Protected Areas", path: "/protected-areas" }, { title: "Zones", path: "/zones" }, { title: "Habitats", path: "/habitats" }, { title: "Conservation Programmes", path: "/programmes" }] }
];

const pageTitleMap = Object.fromEntries([
  ["/", "Dashboard"],
  ["/assistant", "AI Assistant"],
  ...pages.map(page => [page.path, page.title])
]);

export default function App() {
  const location = useLocation();
  const [mobileNavOpen, setMobileNavOpen] = useState(false);
  const [sidebarCollapsed, setSidebarCollapsed] = useState(false);
  const [expandedGroups, setExpandedGroups] = useState({
    dashboard: true,
    wildlife: true,
    operations: true,
    conservation: true
  });

  const activePath = location.pathname;
  const activeGroup = [...navGroups].reverse().find(group => group.items.some(item => item.path === activePath));

  useEffect(() => {
    if (!activeGroup) return;
    setExpandedGroups(prev => ({ ...prev, [activeGroup.id]: true }));
  }, [activeGroup]);

  const toggleGroup = (groupId) => {
    setExpandedGroups(prev => ({ ...prev, [groupId]: !prev[groupId] }));
  };

  const title = pageTitleMap[activePath] || "WildGuard";

  return <div className="layout">
    <div className={`sidebar-backdrop ${mobileNavOpen ? "open" : ""}`} onClick={() => setMobileNavOpen(false)} />
    <aside className={`sidebar ${sidebarCollapsed ? "collapsed" : ""} ${mobileNavOpen ? "open" : ""}`}>
      <div className="brand-row">
        <div className="brand">
          <span className="logo">W</span>
          <div className="brand-copy">
            <b>WildGuard</b>
            <small>Conservation Portal</small>
          </div>
        </div>
        <button type="button" className="sidebar-collapse desktop-only" aria-label="Toggle sidebar" onClick={() => setSidebarCollapsed(value => !value)}>
          {sidebarCollapsed ? "›" : "‹"}
        </button>
      </div>

      <nav className="sidebar-nav">
        {navGroups.map(group => {
          const expanded = !!expandedGroups[group.id];
          const groupActive = group.items.some(item => item.path === activePath);

          return <div key={group.id} className={`nav-group ${groupActive ? "active" : ""}`}>
            <button type="button" className={`group-toggle ${expanded ? "open" : ""}`} onClick={() => toggleGroup(group.id)}>
              <span className="group-label">{group.title}</span>
              <span className="chevron">⌃</span>
            </button>

            <div className={`nav-sublist ${expanded ? "open" : ""}`}>
              {group.items.map(item => (
                <NavLink
                  key={item.path}
                  to={item.path}
                  end={item.path === "/"}
                  onClick={() => setMobileNavOpen(false)}
                  className={({ isActive }) => isActive ? "nav-item active" : "nav-item"}
                >
                  <span className="nav-dot" />
                  <span>{item.title}</span>
                </NavLink>
              ))}
            </div>
          </div>;
        })}
      </nav>

      <div className="side-foot">
        <span className="side-foot-mark">●</span>
        <div>
          <strong>Local services</strong>
          <small>React + Spring Boot</small>
        </div>
      </div>
    </aside>

    <main className="main">
      <header className="topbar">
        <div className="topbar-left">
          <button type="button" className="mobile-menu-button" aria-label="Open navigation" onClick={() => setMobileNavOpen(value => !value)}>☰</button>
          <div>
            <small>WILDLIFE CONSERVATION SYSTEM</small>
            <h1>{title}</h1>
          </div>
        </div>
        <span className="dev-badge">● Development</span>
      </header>

      <div className="content">
        <Routes>
          <Route path="/" element={<Dashboard pages={pages} />} />
          <Route path="/assistant" element={<Dashboard pages={pages} />} />
          {pages.map(page => <Route key={page.path} path={page.path} element={<CrudPage page={page} />} />)}
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </div>

      <footer>WildGuard · Smart Wildlife Conservation & Protected Area Management</footer>
    </main>
  </div>;
}
