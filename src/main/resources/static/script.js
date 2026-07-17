const API = 'http://localhost:8080';
const CAT_ICON = '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><path d="M5 9 L8 4 L9.5 10"/><path d="M19 9 L16 4 L14.5 10"/><circle cx="12" cy="14" r="7.5"/></svg>';

let cats = [];
const screenList = document.getElementById('screen-list');

async function loadCats() {
  const res = await fetch(`${API}/cats`);
  cats = await res.json();
  renderCatList();
}

function renderCatList() {
  
  screenList.querySelectorAll('.cat-row').forEach(el => el.remove());

  cats.forEach(cat => {
    const btn = document.createElement('button');
    btn.className = 'cat-row';
    btn.dataset.catId = cat.id;
    btn.innerHTML = `
      <span class="avatar">${CAT_ICON}</span>
      <span class="cat-name">${cat.name}</span>
      <span class="balance">${cat.treat} treats</span>
    `;
    btn.addEventListener('click', () => openSendScreen(cat.id));
    screenList.appendChild(btn);
  });
}

loadCats();