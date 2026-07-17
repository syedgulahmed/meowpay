const API = 'http://localhost:8080';
const CAT_ICON = '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><path d="M5 9 L8 4 L9.5 10"/><path d="M19 9 L16 4 L14.5 10"/><circle cx="12" cy="14" r="7.5"/></svg>';

let cats = [];
let currentSenderId = null;
let currentIdempotencyKey = null;

const screenList = document.getElementById('screen-list');
const screenSend = document.getElementById('screen-send');

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

function openSendScreen(catId) {
  currentSenderId = catId;
  currentIdempotencyKey = crypto.randomUUID();

  const sender = cats.find(c => c.id === catId);

  document.getElementById('sender-name').textContent = sender.name;
  document.getElementById('sender-balance').textContent = sender.treat;

  const recipientSelect = document.getElementById('recipient');
  recipientSelect.innerHTML = cats
    .filter(c => c.id !== catId)
    .map(c => `<option value="${c.id}">${c.name}</option>`)
    .join('');

  document.getElementById('amount').value = '';
  document.getElementById('status').textContent = '';
  document.getElementById('status').className = 'status';

  screenList.classList.add('hidden');
  screenSend.classList.remove('hidden');
}

function goBack() {
  screenSend.classList.add('hidden');
  screenList.classList.remove('hidden');
}

document.getElementById('back-btn').addEventListener('click', goBack);

loadCats();