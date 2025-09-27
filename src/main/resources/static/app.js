let currentUser = 'user1';

function loadUserData() {
    currentUser = document.getElementById('userSelect').value;
    loadBalance();
    loadHistory();
}

async function loadBalance() {
    try {
        const response = await fetch(`/api/points/${currentUser}`);
        const data = await response.json();
        document.getElementById('balance').textContent = data.balance.toLocaleString();
    } catch (error) {
        console.error('残高取得エラー:', error);
    }
}

async function loadHistory() {
    try {
        const response = await fetch(`/api/points/${currentUser}/history`);
        const history = await response.json();
        
        const historyDiv = document.getElementById('history');
        historyDiv.innerHTML = '';
        
        history.forEach(item => {
            const div = document.createElement('div');
            div.className = 'history-item';
            
            const date = new Date(item.createdAt).toLocaleDateString('ja-JP');
            const pointsClass = item.type === 'ADD' ? 'points-add' : 'points-use';
            const pointsSign = item.type === 'ADD' ? '+' : '-';
            
            div.innerHTML = `
                <div class="d-flex justify-content-between">
                    <span>${date}</span>
                    <span class="${pointsClass}">${pointsSign}${item.points}pt</span>
                </div>
                <small class="text-muted">${item.description}</small>
            `;
            
            historyDiv.appendChild(div);
        });
    } catch (error) {
        console.error('履歴取得エラー:', error);
    }
}

async function addPoints() {
    const points = parseInt(document.getElementById('addPoints').value);
    const description = document.getElementById('addDescription').value || 'ポイント付与';
    
    if (!points || points <= 0) {
        alert('正しいポイント数を入力してください');
        return;
    }
    
    try {
        const response = await fetch('/api/points/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId: currentUser,
                points: points,
                description: description
            })
        });
        
        if (response.ok) {
            document.getElementById('addPoints').value = '';
            document.getElementById('addDescription').value = '';
            loadUserData();
            alert('ポイントを付与しました');
        }
    } catch (error) {
        console.error('ポイント付与エラー:', error);
    }
}

async function usePoints() {
    const points = parseInt(document.getElementById('usePoints').value);
    const description = document.getElementById('useDescription').value || 'ポイント利用';
    
    if (!points || points <= 0) {
        alert('正しいポイント数を入力してください');
        return;
    }
    
    try {
        const response = await fetch('/api/points/use', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId: currentUser,
                points: points,
                description: description
            })
        });
        
        const message = await response.text();
        
        if (response.ok) {
            document.getElementById('usePoints').value = '';
            document.getElementById('useDescription').value = '';
            loadUserData();
        }
        
        alert(message);
    } catch (error) {
        console.error('ポイント利用エラー:', error);
    }
}

// 初期データ読み込み
document.addEventListener('DOMContentLoaded', function() {
    loadUserData();
});