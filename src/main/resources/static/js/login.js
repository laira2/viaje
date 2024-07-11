@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap');

:root {
    --space-root: 1rem;
    --space-xs: calc(var(--space-root) / 2);
    --space-s: calc(var(--space-root) / 1.5);
    --space-m: var(--space-root);
    --space-l: calc(var(--space-root) * 1.5);
    --space-xl: calc(var(--space-root) * 2);
    --color-primary: mediumslateblue;
    --color-secondary: black;
    --color-tertiary: hotpink;
    --base-border-radius: 0.25rem;
    --ease: cubic-bezier(0.075, 0.82, 0.165, 1);
    --duration: 350ms;
    --font-family: 'Roboto', sans-serif;
    --font-size: 1.25rem;
}

/* 공통 스타일 */
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    display: grid;
    place-items: center;
    margin: 0;
    height: 100vh;
    padding: var(--space-m);
    font-size: var(--font-size);
    font-family: var(--font-family);
    line-height: 1.2;
    background-color: var(--color-tertiary);
}

a {
    color: var(--color-primary);
    &:focus {
        color: var(--color-secondary);
    }
}

.background {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: url('/static/images/start/background2.png') no-repeat center 70%/cover;
    z-index: 0;
}

.container {
    position: relative;
    z-index: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.viaje-logo {
    position: absolute;
    top: 50px;
    left: -20%; /* 5%로 설정하여 왼쪽으로 이동 */
    z-index: 2;
}

.form-wrapper {
    position: relative;
    z-index: 1;
    margin-top: 100px;
}


h2 {
    font-weight: 700;
    font-size: calc(var(--font-size) * 1.5);
}

.form {
    position: relative;
    width: 100%;
    max-width: 450px;
    margin: 0 auto;
    transform: skewY(-5deg) translateY(10%) scale(0.94);
    transition:
        box-shadow var(--duration) var(--ease),
        transform var(--duration) var(--ease);

    &:before,
    &:after {
        content: '';
        position: absolute;
        pointer-events: none;
        background-color: rgba(255, 255, 255, 0.8);
        width: 25%;
        height: 100%;
        transition:
            background-color var(--duration) var(--ease),
            transform var(--duration) var(--ease);
    }

    &:before {
        top: 0;
        right: calc(100% - 1px);
        transform-origin: 100% 100%;
        transform: skewY(-35deg) scaleX(-1);
        z-index: -1;
    }

    &:after {
        top: 0;
        left: calc(100% - 1px);
        transform-origin: 0 0;
        transform: skewY(-35deg) scaleX(-1);
        z-index: 2;
    }

    &:hover,
    &:focus-within {
        transform: scale(1.0001);
        box-shadow: 0 1rem 3rem rgba(0, 0, 0, 0.1);

        &:before,
        &:after {
            background-color: white;
            transform: skewY(0);
        }
    }
}

.form-inner {
    padding: var(--space-xl);
    background-color: white;
    z-index: 1;
}

.input-wrapper {
    &:focus-within {
        label {
            color: var(--color-secondary);
        }

        .icon {
            background-color: var(--color-secondary);
        }

        input {
            border-color: var(--color-secondary);
        }
    }

    + .input-wrapper {
        margin-top: var(--space-l);
    }
}

.input-group {
    position: relative;

    input {
        border-radius: var(--base-border-radius);
        padding-left: calc(var(--space-s) + 60px);
    }

    .icon {
        position: absolute;
        top: 0;
        left: 0;
        height: 100%;
        border-top-left-radius: var(--base-border-radius);
        border-bottom-left-radius: var(--base-border-radius);
        pointer-events: none;
    }
}

label {
    font-size: calc(var(--font-size) / 1.65);
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 0.065rem;
    display: block;
    margin-bottom: var(--space-xs);
    color: var(--color-primary);
}

.icon {
    display: flex;
    align-items: center;
    flex: 0 1 auto;
    padding: var(--space-m);
    background-color: var(--color-primary);

    svg {
        width: 1.25em;
        height: 1.25em;
        fill: white;
        pointer-events: none;
        user-select: none;
        transition: transform var(--duration) var(--ease);
    }
}

input {
    flex: 1 1 0;
    width: 100%;
    outline: none;
    padding: var(--space-m);
    font-size: var(--font-size);
    font-family: var(--font-family);
    color: var(--color-secondary);
    border: 2px solid var(--color-primary);

    &:focus {
        color: var(--color-primary);
    }
}

.btn-group {
    display: flex;
    align-items: center;
    justify-content: space-between;

    > * + * {
        margin-left: var(--space-s);
    }
}

.btn {
    position: relative;
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: space-between;
    outline: none;
    padding: var(--space-m) var(--space-l);
    cursor: pointer;
    border: 2px solid transparent;
    border-radius: var(--base-border-radius);
}

.btn--primary {
    background-color: var(--color-primary);
    border-color: var(--color-primary);
    color: white;
    font-size: calc(var(--font-size) / 1.65);
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 0.065rem;
    cursor: pointer;
    transition: background-color var(--duration) var(--ease);
}

.btn--primary:focus {
    background-color: var(--color-secondary);
    border-color: var(--color-secondary);
}

.btn--text {
    font-size: calc(var(--font-size) / 1.5);
    padding: 0;
    color: var(--color-primary);
    text-decoration: none;
}

.btn--text:hover {
    text-decoration: underline;
}