import React, { useCallback, useEffect, useState } from "react";

export default function CrudPage({ page }) {
  const blank = () => Object.fromEntries(page.fields.map(([name]) => [name, ""]));
  const [records, setRecords] = useState([]);
  const [form, setForm] = useState(blank);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const load = useCallback(async () => {
    setLoading(true); setError("");
    try {
      const response = await fetch(page.url);
      if (!response.ok) throw new Error(`HTTP ${response.status}`);
      const data = await response.json();
      setRecords(Array.isArray(data) ? data : []);
    } catch (e) { setError(`${e.message}. Check that the service is running and the endpoint is correct.`); }
    finally { setLoading(false); }
  }, [page.url]);
  useEffect(() => { load(); }, [load]);

  function edit(record) {
    const values = blank();
    page.fields.forEach(([name]) => {
      if (name === "speciesId" && record.species) values[name] = record.species.id ?? "";
      else values[name] = record[name] ?? "";
    });
    setForm(values); setEditId(record.id); setMessage("");
    window.scrollTo({ top: 0, behavior: "smooth" });
  }
  function cancel() { setForm(blank()); setEditId(null); }

  async function save(event) {
    event.preventDefault(); setError(""); setMessage("");
    const body = {};
    page.fields.forEach(([name,,type]) => {
      if (form[name] !== "") body[name] = type === "number" ? Number(form[name]) : form[name];
    });
    if (page.title === "Animals" && body.speciesId) {
      body.species = { id: Number(body.speciesId) }; delete body.speciesId;
    }
    try {
      const response = await fetch(editId ? `${page.url}/${editId}` : page.url, {
        method: editId ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" }, body: JSON.stringify(body)
      });
      if (!response.ok) throw new Error((await response.text()) || `HTTP ${response.status}`);
      setMessage(editId ? "Record updated." : "Record created."); cancel(); await load();
    } catch (e) { setError(`Could not save record: ${e.message}`); }
  }

  async function remove(id) {
    if (!confirm("Delete this record?")) return;
    setError(""); setMessage("");
    try {
      const response = await fetch(`${page.url}/${id}`, { method: "DELETE" });
      if (!response.ok) throw new Error(`HTTP ${response.status}`);
      setMessage("Record deleted."); await load();
    } catch (e) { setError(`Could not delete record: ${e.message}`); }
  }

  return <>
    <div className="page-head"><div><h2>Manage {page.title.toLowerCase()}</h2><p>Create, view, update and delete records.</p></div><button className="secondary button" onClick={load}>↻ Refresh</button></div>
    <section className="panel form-panel"><div className="section-head"><div><h2>{editId ? `Edit record #${editId}` : "Add a record"}</h2><p>Fill in the details below.</p></div></div>
      <form onSubmit={save}><div className="fields">{page.fields.map(([name,label,type]) => <label key={name}>{label}<input type={type || "text"} value={form[name]} onChange={e => setForm({...form,[name]:e.target.value})}/></label>)}</div>
        <div className="actions"><button className="button primary">{editId ? "Save changes" : "Add record"}</button>{editId && <button type="button" className="button secondary" onClick={cancel}>Cancel</button>}</div>
      </form>
    </section>
    {message && <p className="message success">{message}</p>}{error && <p className="message error">{error}</p>}
    <section className="panel table-panel"><div className="section-head"><div><h2>Records</h2><p>{records.length} record(s)</p></div></div>
      {loading ? <p className="empty">Loading…</p> : records.length === 0 ? <p className="empty">No records returned.</p> :
      <div className="table-scroll"><table><thead><tr><th>ID</th>{page.fields.map(([name,label]) => <th key={name}>{label}</th>)}<th>Actions</th></tr></thead>
        <tbody>{records.map(record => <tr key={record.id}><td>#{record.id}</td>{page.fields.map(([name]) => { let value = name === "speciesId" && record.species ? record.species.id : record[name]; if (value && typeof value === "object") value = JSON.stringify(value); return <td key={name}>{value ?? "—"}</td>; })}<td><div className="actions"><button className="small" onClick={() => edit(record)}>Edit</button><button className="small delete" onClick={() => remove(record.id)}>Delete</button></div></td></tr>)}</tbody>
      </table></div>}
    </section><p className="hint">API: <code>{page.url}</code></p>
  </>;
}
