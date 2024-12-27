
document.addEventListener('DOMContentLoaded', function() {
    const authSection = document.getElementById('auth-section');
    const blogSection = document.getElementById('blog-section');
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const blogForm = document.getElementById('blog-form');
    const authMessage = document.getElementById('auth-message');
    const userPosts = document.getElementById('user-posts');
    const allPosts = document.getElementById('all-posts');
    const logoutBtn = document.getElementById('logout-btn');
    const showLoginBtn = document.getElementById('show-login');
    const showRegisterBtn = document.getElementById('show-register');

    showLoginBtn.addEventListener('click', () => {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
        showLoginBtn.classList.add('active');
        showRegisterBtn.classList.remove('active');
    });

    showRegisterBtn.addEventListener('click', () => {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
        showLoginBtn.classList.remove('active');
        showRegisterBtn.classList.add('active');
    });

    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const username = document.getElementById('login-username').value;
        const password = document.getElementById('login-password').value;
        login(username, password);
    });

    registerForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const username = document.getElementById('register-username').value;
        const password = document.getElementById('register-password').value;
        register(username, password);
    });

    blogForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const title = document.getElementById('blog-title').value;
        const content = document.getElementById('blog-content').value;
        createBlogPost(title, content);
    });

    logoutBtn.addEventListener('click', logout);

    function login(username, password) {
        fetch('/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                localStorage.setItem('token', data.token);
                localStorage.setItem('username', username);
                showBlogSection();
            } else {
                authMessage.textContent = 'Invalid credentials';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            authMessage.textContent = 'An error occurred';
        });
    }

    function register(username, password) {
        fetch('/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                authMessage.textContent = 'Registration successful. Please login.';
                showLoginBtn.click();
            } else {
                authMessage.textContent = 'Registration failed';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            authMessage.textContent = 'An error occurred';
        });
    }

    function createBlogPost(title, content) {
        fetch('/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            body: JSON.stringify({ title, content })
        })
        .then(response => response.json())
        .then(data => {
            console.log('Blog post created:', data);
            fetchBlogPosts();
        })
        .catch(error => console.error('Error:', error));
    }

    function fetchBlogPosts() {
        fetch('/api/posts', {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        })
        .then(response => response.json())
        .then(data => {
            displayBlogPosts(data, allPosts);
        })
        .catch(error => console.error('Error:', error));

        fetch('/api/posts/user', {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        })
        .then(response => response.json())
        .then(data => {
            displayBlogPosts(data, userPosts);
        })
        .catch(error => console.error('Error:', error));
    }

    function displayBlogPosts(posts, container) {
        container.innerHTML = '';
        posts.forEach(post => {
            const postElement = document.createElement('div');
            postElement.classList.add('blog-post');
            postElement.innerHTML = `
                <h4>${post.title}</h4>
                <p>${post.content}</p>
                <small>Author: ${post.author}</small>
            `;
            container.appendChild(postElement);
        });
    }

    function showBlogSection() {
        authSection.style.display = 'none';
        blogSection.style.display = 'block';
        document.getElementById('user-name').textContent = localStorage.getItem('username');
        fetchBlogPosts();
    }

    function logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('username');
        authSection.style.display = 'block';
        blogSection.style.display = 'none';
        authMessage.textContent = '';
    }

    // Check if user is already logged in
    if (localStorage.getItem('token')) {
        showBlogSection();
    }
});