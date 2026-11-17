# TiendaMass — Migración de Java a Python (Trabajo en Equipo)

Repositorio del proyecto **Minimarket Tienda Mass**, organizado en dos carpetas principales:

- `modelojava/` — Proyecto original en **Java (Swing + NetBeans)**. **Solo referencia: no modificar ni borrar archivos aquí.**
- `ModeloPython/` — Espacio para la migración a **Python 3.12+**. Cada integrante trabaja libremente aquí dentro de su propia rama.

---

## 1. Ramas del repositorio (`main` → `dev` → `feature/*`)

- **`main`**: Rama principal estable (recibe la versión final combinada desde `dev`).
- **`dev`**: Rama de integración del equipo. **Al finalizar, todas las ramas personales se unirán hacia `dev` para combinar el trabajo de todos.**
- **Ramas individuales (cada integrante avanza a su manera en su rama):**
  - `feature/rai-de-la-cruz` — Rai De La Cruz
  - `feature/pretel-enrique` — Pretel Enrique
  - `feature/andre-cortez` — Andre Cortez
  - `feature/gia-sanchez` — Gia Sanchez
  - `feature/alessandra-condeso` — Alessandra Condeso

---

## 2. Cómo empezar en tu computadora (para cada integrante)

### Paso 1: Instalar Python
1. Instala **Python 3.12** (en Windows puedes ejecutar `winget install -e --id Python.Python.3.12` o descargarlo desde [python.org](https://www.python.org/) marcando **"Add python.exe to PATH"**).
2. Verifica en tu terminal que Python y `tkinter` funcionen:
   ```powershell
   python --version
   python -m tkinter
   ```

### Paso 2: Clonar el repo y entrar a tu rama
1. Clona el repositorio:
   ```powershell
   git clone https://github.com/raidelacruz/TiendaMass.git
   cd TiendaMass
   ```
2. Descarga las ramas y cámbiate a **tu propia rama** (ejemplo con `pretel-enrique`):
   ```powershell
   git fetch --all
   git checkout feature/pretel-enrique
   ```
3. Crea y activa tu entorno virtual dentro de `ModeloPython/` (solo en tu PC; no se sube a GitHub):
   ```powershell
   cd ModeloPython
   python -m venv .venv
   .\.venv\Scripts\Activate.ps1
   ```

### Paso 3: Trabajar y subir tus avances
Avanza en tu propia migración dentro de `ModeloPython/` usando `modelojava/` como referencia. Cuando quieras guardar tus cambios en GitHub:
```powershell
git status
git add ModeloPython/
git commit -m "feat: avance de migracion"
git push -u origin feature/tu-nombre
```

---

## 3. Reglas básicas para poder combinar todo al final

Para evitar problemas al hacer `merge` hacia `dev`:
1. **Trabaja siempre en tu propia rama** (`git branch` para verificar antes de hacer commit).
2. **No modifiques `modelojava/`**, así mantenemos intacta la versión original como referencia.
3. **Trabaja todo dentro de `ModeloPython/`** (usa nombres de archivos en minúsculas `snake_case`, sin espacios, sin tildes y sin `ñ`).
4. **Lee y guarda archivos `.txt` con `encoding="utf-8"`** para no perder tildes ni `ñ` de los datos.
5. **No subas `.venv/` ni `__pycache__/`** (ya están ignorados en `.gitignore`).
6. Cuando terminemos, abriremos Pull Requests hacia **`dev`** para revisar, resolver conflictos y combinar lo mejor de cada avance.
