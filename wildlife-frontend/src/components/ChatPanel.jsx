import React, { useMemo, useRef, useState } from "react";

const suggestedPrompts = [
  "Give me an update on animal 1051",
  "Summarize Zone 3",
  "What are the recent incidents?",
  "Which protected areas need attention?",
  "Summarize recent patrol activity"
];

export default function ChatPanel() {
  const [messages, setMessages] = useState([
    {
      role: "assistant",
      text: "Hello! I'm your WildGuard conservation assistant. Ask me about an animal, a zone, a protected area, or recent field activity."
    }
  ]);
  const [input, setInput] = useState("");
  const [isTyping, setIsTyping] = useState(false);
  const textareaRef = useRef(null);

  const welcomeMessage = useMemo(
    () => messages.length === 1 && messages[0].role === "assistant",
    [messages]
  );

  const handleSubmit = async (value = input) => {
    const trimmed = value.trim();
    if (!trimmed || isTyping) return;

    setMessages(prev => [...prev, { role: "user", text: trimmed }]);
    setInput("");
    setIsTyping(true);

    try {
      const response = await fetch("http://localhost:8083/api/assistant/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ message: trimmed })
      });

      const payload = await response.json().catch(() => ({}));

      if (!response.ok) {
        const messageText = payload?.error || payload?.message || `Request failed (${response.status})`;
        throw new Error(messageText);
      }

      const answer = typeof payload.answer === "string" && payload.answer.trim()
        ? payload.answer.trim()
        : "I couldn’t find a clear answer for that request.";

      setMessages(prev => [...prev, { role: "assistant", text: answer }]);
    } catch (error) {
      setMessages(prev => [...prev, {
        role: "assistant",
        text: `I couldn't answer that right now. ${error.message}`
      }]);
    } finally {
      setIsTyping(false);
    }
  };

  const handleKeyDown = (event) => {
    if (event.key === "Enter" && !event.shiftKey) {
      event.preventDefault();
      handleSubmit();
    }
  };

  return <section className="panel chat-panel">
    <div className="chat-header">
      <div className="chat-header-copy">
        <span className="chat-badge">AI</span>
        <div>
          <h3>Ask WildGuard AI</h3>
          <p>Explore wildlife records, investigate incidents and understand conservation risks.</p>
        </div>
      </div>
    </div>

    <div className="chat-shell">
      {welcomeMessage && <div className="chat-empty-state"><strong>Ready to assist</strong><p>Ask about species, incidents, patrols or protected areas.</p></div>}

      <div className="chat-messages">
        {messages.map((message, index) => (
          <div key={`${message.role}-${index}`} className={`message-row ${message.role}`}>
            <div className="message-bubble">
              {message.text}
            </div>
          </div>
        ))}
        {isTyping && <div className="message-row assistant"><div className="message-bubble typing"><span /><span /><span /></div></div>}
      </div>

      <div className="suggested-prompts">
        {suggestedPrompts.map(prompt => (
          <button key={prompt} type="button" className="chip" onClick={() => {
            setInput(prompt);
            textareaRef.current?.focus();
          }}>
            {prompt}
          </button>
        ))}
      </div>

      <div className="chat-input-bar">
        <textarea
          ref={textareaRef}
          value={input}
          onChange={event => setInput(event.target.value)}
          onKeyDown={handleKeyDown}
          rows={1}
          placeholder="Ask WildGuard AI about wildlife activity..."
        />
        <button type="button" className="send-button" onClick={() => handleSubmit()} aria-label="Send message">
          ➤
        </button>
      </div>
    </div>
  </section>;
}
