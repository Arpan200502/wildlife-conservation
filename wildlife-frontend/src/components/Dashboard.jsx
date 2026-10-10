import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import ChatPanel from "./ChatPanel";

const pageMap = {
  Animals: { path: "/animals" },
  Patrols: { path: "/patrols" },
  Incidents: { path: "/incidents" },
  "Protected Areas": { path: "/protected-areas" }
};

const servicePages = {
  animals: "Animals",
  incidents: "Incidents",
  patrols: "Patrols",
  rescues: "Rescues",
  species: "Species",
  protectedAreas: "Protected Areas",
  habitats: "Habitats",
  programmes: "Conservation Programmes"
};

export default function Dashboard({ pages }) {
  const [data, setData] = useState({
    animals: [],
    incidents: [],
    patrols: [],
    rescues: [],
    species: [],
    protectedAreas: [],
    habitats: [],
    programmes: []
  });
  const [loading, setLoading] = useState(true);
  const [lastRefresh, setLastRefresh] = useState("");
  const [errors, setErrors] = useState([]);

  const loadDashboard = async () => {
    const endpoints = [
      { key: "animals", url: pages.find(page => page.title === "Animals")?.url },
      { key: "incidents", url: pages.find(page => page.title === "Incidents")?.url },
      { key: "patrols", url: pages.find(page => page.title === "Patrols")?.url },
      { key: "rescues", url: pages.find(page => page.title === "Rescues")?.url },
      { key: "species", url: pages.find(page => page.title === "Species")?.url },
      { key: "protectedAreas", url: pages.find(page => page.title === "Protected Areas")?.url },
      { key: "habitats", url: pages.find(page => page.title === "Habitats")?.url },
      { key: "programmes", url: pages.find(page => page.title === "Conservation Programmes")?.url }
    ];

    setLoading(true);
    setErrors([]);

    const results = await Promise.allSettled(
      endpoints.map(async endpoint => {
        if (!endpoint.url) {
          return { key: endpoint.key, value: [] };
        }

        const response = await fetch(endpoint.url);
        if (!response.ok) {
          throw new Error(`${servicePages[endpoint.key]} service returned ${response.status}`);
        }

        const json = await response.json();
        return { key: endpoint.key, value: Array.isArray(json) ? json : [] };
      })
    );

    const nextData = { animals: [], incidents: [], patrols: [], rescues: [], species: [], protectedAreas: [], habitats: [], programmes: [] };
    const nextErrors = [];

    results.forEach(result => {
      if (result.status === "fulfilled") {
        nextData[result.value.key] = result.value.value;
      } else {
        nextErrors.push(result.reason.message);
      }
    });

    setData(nextData);
    setErrors(nextErrors);
    setLastRefresh(new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" }));
    setLoading(false);
  };

  useEffect(() => { loadDashboard(); }, []);

  const animalCount = data.animals.length;
  const normalizeStatusClass = (value) => String(value || "").trim().toLowerCase().replace(/\s+/g, "-");

  const openIncidentCount = data.incidents.filter(item => {
    const status = String(item.status || "").toLowerCase();
    return status !== "resolved" && status !== "closed";
  }).length;
  const activePatrolCount = data.patrols.filter(item => {
    const status = String(item.status || "").toLowerCase();
    return status.includes("in-progress") || status.includes("in progress") || status.includes("scheduled") || status.includes("on-patrol") || status.includes("on patrol");
  }).length;
  const protectedAreasCount = data.protectedAreas.length;

  const recentIncidents = [...(data.incidents || [])]
    .sort((a, b) => new Date(b.reportedDate || b.createdAt || 0) - new Date(a.reportedDate || a.createdAt || 0))
    .slice(0, 4);

  const recentPatrols = [...(data.patrols || [])]
    .sort((a, b) => new Date(b.patrolDate || b.createdAt || 0) - new Date(a.patrolDate || a.createdAt || 0))
    .slice(0, 4);

  const recentRescues = [...(data.rescues || [])]
    .sort((a, b) => new Date(b.rescueDate || b.createdAt || 0) - new Date(a.rescueDate || a.createdAt || 0))
    .slice(0, 3);

  const stats = [
    { label: "Animals Tracked", value: animalCount, icon: "🐾", path: pageMap["Animals"].path, note: "Wildlife records" },
    { label: "Open Incidents", value: openIncidentCount, icon: "⚠", path: pageMap["Incidents"].path, note: "Requiring action" },
    { label: "Active Patrols", value: activePatrolCount, icon: "🛰", path: pageMap["Patrols"].path, note: "Field operations" },
    { label: "Protected Areas", value: protectedAreasCount, icon: "🛡", path: pageMap["Protected Areas"].path, note: "Managed reserves" }
  ];

  const secondaryStats = [
    { label: "Rescue Cases", value: data.rescues.length || 0, path: "/rescues" },
    { label: "Species Tracked", value: data.species.length || 0, path: "/species" },
    { label: "Habitats", value: data.habitats.length || 0, path: "/habitats" },
    { label: "Programmes", value: data.programmes.length || 0, path: "/programmes" }
  ];

  return <>
    <section className="hero-panel">
      <div className="hero-copy">
        <span className="tag">CONSERVATION OPERATIONS</span>
        <h2>Conservation Command Centre</h2>
        <p>Monitor wildlife, operations and conservation priorities.</p>
      </div>
      <div className="hero-actions">
        <button type="button" className="button primary" onClick={loadDashboard} disabled={loading}>
          {loading ? "Refreshing..." : "Refresh data"}
        </button>
        <div className="status-chip">
          <span className={`status-dot ${errors.length ? "error" : "ok"}`} />
          {loading ? "Syncing" : errors.length ? "Data issue" : "Live"}
        </div>
      </div>
      <div className="hero-meta">
        <span>Last refresh: {lastRefresh || "Waiting..."}</span>
        {errors.length > 0 && <span className="meta-error">{errors[0]}</span>}
      </div>
    </section>

    <section className="stats-grid">
      {stats.map(item => (
        <Link key={item.label} to={item.path} className="stat-card">
          <div className="stat-topline">
            <span className="stat-icon">{item.icon}</span>
            <span className="stat-note">{item.note}</span>
          </div>
          <strong>{loading ? "…" : item.value}</strong>
          <span>{item.label}</span>
        </Link>
      ))}
    </section>

    <section className="secondary-metrics">
      {secondaryStats.map(item => (
        <Link key={item.label} to={item.path} className="mini-stat">
          <span>{item.label}</span>
          <strong>{loading ? "…" : item.value}</strong>
        </Link>
      ))}
    </section>

    <section className="overview-grid">
      <div className="panel panel-stack">
        <div className="section-head compact">
          <div>
            <h2>Recent incidents</h2>
            <p>Latest reported field activity</p>
          </div>
        </div>
        <div className="list-stack">
          {recentIncidents.length ? recentIncidents.map(item => (
            <div key={item.id || item.incidentCode || item.incidentType} className="activity-item">
              <div>
                <strong>{item.incidentCode || item.incidentType || "Incident"}</strong>
                <small>{item.location || "Location unavailable"}</small>
              </div>
              <div className="item-badges">
                <span className={`badge severity ${normalizeStatusClass(item.severity || "medium")}`}>{item.severity || "Medium"}</span>
                <span className={`badge status ${normalizeStatusClass(item.status || "open")}`}>{item.status || "Open"}</span>
              </div>
            </div>
          )) : <p className="empty">No recent incidents available.</p>}
        </div>
      </div>

      <div className="panel panel-stack">
        <div className="section-head compact">
          <div>
            <h2>Recent patrols</h2>
            <p>Operational coverage</p>
          </div>
        </div>
        <div className="list-stack">
          {recentPatrols.length ? recentPatrols.map(item => (
            <div key={item.id || item.patrolCode || item.patrolDate} className="activity-item">
              <div>
                <strong>{item.patrolCode || "Patrol"}</strong>
                <small>{item.patrolDate || "Date unavailable"}</small>
              </div>
              <div className="item-badges">
                <span className={`badge status ${normalizeStatusClass(item.status || "scheduled")}`}>{item.status || "Scheduled"}</span>
              </div>
            </div>
          )) : <p className="empty">No patrol records available.</p>}
        </div>
      </div>

      <div className="panel panel-stack">
        <div className="section-head compact">
          <div>
            <h2>Recent rescues</h2>
            <p>Wildlife response activity</p>
          </div>
        </div>
        <div className="list-stack">
          {recentRescues.length ? recentRescues.map(item => (
            <div key={item.id || item.animalId || item.rescueDate} className="activity-item">
              <div>
                <strong>{item.location || "Rescue location"}</strong>
                <small>{item.rescueDate || "Date unavailable"}</small>
              </div>
              <div className="item-badges">
                <span className="badge status active">{item.rehabilitationStatus || "Monitoring"}</span>
              </div>
            </div>
          )) : <p className="empty">No rescue records available.</p>}
        </div>
      </div>

      <div className="panel panel-stack">
        <div className="section-head compact">
          <div>
            <h2>Conservation activity</h2>
            <p>Protected area operations</p>
          </div>
        </div>
        <div className="activity-summary">
          <div className="summary-block">
            <span>Protected areas</span>
            <strong>{protectedAreasCount}</strong>
          </div>
          <div className="summary-block">
            <span>Species tracked</span>
            <strong>{data.species.length || 0}</strong>
          </div>
          <div className="summary-block">
            <span>Programmes</span>
            <strong>{data.programmes.length || 0}</strong>
          </div>
          <div className="summary-block">
            <span>Habitats</span>
            <strong>{data.habitats.length || 0}</strong>
          </div>
        </div>
      </div>
    </section>

    <section className="chat-outer">
      <div className="chat-intro">
        <div>
          <strong className="chat-kicker">AI COMMAND</strong>
          <h2>Ask WildGuard AI</h2>
        </div>
      </div>
      <div className="chat-shell-wrapper">
        <ChatPanel />
      </div>
    </section>
  </>;
}
