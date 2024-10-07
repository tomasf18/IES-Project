# Docusaurus

## Overview

Docusaurus is an open-source tool designed for building optimized websites, primarily used for documentation. It's built using React. Docusaurus aims to make it easy for developers to create fast, content-driven websites with minimal setup.

### What Docusaurus Is:
1. **Static Site Generator**: Docusaurus takes your content (like Markdown or MDX files) and generates a static website, which can then be hosted anywhere (e.g., GitHub Pages, Netlify).
2. **Built-in Documentation Support**: It offers a specialized structure for creating documentation, including navigation, versioning, and search capabilities out of the box.
3. **Customization Options**: Since it’s built on React, you can extend and customize it with React components, making it highly flexible for your website needs.
4. **Markdown and MDX**: Docusaurus supports Markdown, but also MDX (Markdown with embedded React components), which makes it more powerful when you want to include interactive content.


## Important Tools And Features For This Project

### 1. **Documentation**:
   - **MDX Support**: Write documentation in Markdown or MDX (Markdown with tsX support for React components).
   - **Versioning**: Easily create and manage versions of your documentation, which is useful for maintaining different versions of your product/project.
   - **Sidebars**: Docusaurus automatically generates sidebars from your file structure, but you can customize them if needed.
   - **Multi-language Support**: Built-in localization tools allow you to create multi-language documentation using tsON files.
   - **Search**: Docusaurus integrates with Algolia Search out of the box to provide a fast and accurate search feature.

### 2. **Blogging**:
   - **Blog System**: You can create and manage blogs with support for features like tags, categories, RSS feeds, and pagination.
   - **MDX for Blogs**: Like documentation, blog posts can be written using MDX, allowing you to embed React components inside them.
   - **Authors and Metadata**: Easily add author information and post metadata (dates, categories, tags) for each blog post.

### 3. **Theming and Customization**:
   - **Themes**: Docusaurus has a default theme, but you can also create custom themes or use community themes.
   - **Custom CSS/React Components**: You can inject custom CSS or use React to customize your website, from individual pages to the entire layout.
   - **Theme Components**: Overwrite or extend default components with your own React components to fully customize the look and feel of your site.

### 4. **Plugins**:
   - **Built-in Plugins**: Docusaurus provides many built-in plugins, such as for sitemap generation, Google Analytics integration, and PWA (Progressive Web App) support.
   - **Custom Plugins**: You can also create custom plugins to add specific features to your site.
   - **Third-party Integrations**: Docusaurus supports a wide range of third-party services, like Google Analytics, Hotjar, and more, via plugins.

### 5. **Deployment**:
   - **Static Site Generation**: It’s optimized for static site generation (SSG), making it easy to deploy your site anywhere that hosts static files (e.g., GitHub Pages, Netlify, Vercel).
   - **CI/CD Friendly**: Docusaurus can be integrated into any continuous integration/continuous deployment (CI/CD) pipeline for automated deployment.

### 6. **Content Organization**:
   - **Sidebars**: You can automatically generate navigation sidebars from your directory structure or customize them manually.
   - **Docs and Pages Routing**: Docusaurus automatically creates routes for your Markdown and MDX files, making it easy to link between different parts of your website.
   - **Permalinks**: It generates clean, SEO-friendly URLs for all of your pages and blog posts.

### 7. **Customization with React**:
   - **React Components**: Since Docusaurus is based on React, you can create custom components and embed them in your Markdown or MDX files.
   - **Layout Customization**: You can easily customize the layout of your site using React, making it flexible for different content needs.

### 12. **API Documentation**:
   - **OpenAPI Support**: You can integrate OpenAPI specs to generate API documentation for your project, making Docusaurus ideal for developer-centric websites.

### 13. **Deployment Tools**:
   - **GitHub Pages Integration**: Docusaurus provides easy commands to deploy your website to GitHub Pages.
   - **Netlify/Vercel Support**: It is compatible with static site hosting services like Netlify and Vercel, and can be deployed through CI/CD workflows.


## Docusaurus Setup

To create a Docusaurus documentation website, you need to follow these steps:

### 1. **Install Node.ts and Yarn (Optional)**
   Docusaurus requires Node.ts and optionally Yarn as the package manager.

   - **Install Node.ts**:
```bash
# installs nvm (Node Version Manager)
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash

# download and install Node.ts (you may need to restart the terminal)
nvm install 20

# verifies the right Node.ts version is in the environment
node -v # should print `v20.18.0`

# verifies the right npm version is in the environment
npm -v # should print `10.8.2`
```

---
`If th versions are incorrect after the lastt 2 commands, do this:`

1. Run the following commands to load `nvm`:
   ```bash
   export NVM_DIR="$HOME/.nvm"
   [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
   ```

2. **Verify nvm is installed** by running:
   ```bash
   nvm --version
   ```

3. **Use `nvm` to install Node.ts version 20**:
   ```bash
   nvm install 20
   ```

4. **Set Node.ts version 20 as the default**:
   ```bash
   nvm use 20
   nvm alias default 20
   ```

5. **Verify that the correct Node.ts and npm versions are in use**:
   ```bash
   node -v   # should print v20.18.0
   npm -v    # should print 10.8.2
   ```
---

### 2. **Create a New Docusaurus Project**
   Docusaurus provides a command to quickly set up a new project.

   In your terminal, navigate to the directory where you want to create your project and run:

   ```bash
   npx create-docusaurus@latest my-docs-site classic
   ```

   Here:
   - `my-docs-site` is the name of your project (you can replace this with your own project name).
   - `classic` is the template you want to use. Docusaurus provides different templates, but "classic" is a popular starting point.

   This will generate the structure of your Docusaurus site in a new folder named `my-docs-site`.

### 3. **Navigate to the Project Directory**
   After the project is created, move into the project folder:

   ```bash
   cd my-docs-site
   ```

### 4. **Start the Local Development Server**
   You can now run the development server to see your site locally.

   If you’re using npm:
   ```bash
   npm run start
   ```

   This will start the local server, and you can view your Docusaurus documentation site at `http://localhost:3000`.

### 5. **Explore the Project Structure**
   Your project will have a few key folders:
   - **`/docs`**: This is where your Markdown or MDX files for the documentation are stored.
   - **`/src`**: Contains source code for your website, including custom pages and components.
   - **`/blog`**: If you want to have a blog, this folder contains your blog posts (optional).
   - **`/static`**: This folder is for static assets like images, which will be copied to the root of your website.
   - **`/docusaurus.config.ts`**: This is the configuration file where you set up site details (title, theme, plugins, etc.).

### 6. **Add New Documentation Pages**
   To create a new documentation page, simply add a new `.md` or `.mdx` file inside the `/docs` folder.

   For example, you can create a file called `getting-started.md` inside the `docs` folder:

   ```md
   ---
   id: getting-started
   title: Getting Started
   ---

   # Welcome to the Documentation

   This is a new documentation page created using Docusaurus.
   ```

   Once you save the file, Docusaurus will automatically update and include this in the docs section of your site.

### 7. **Customize the Sidebar**
   The sidebar is automatically generated based on your file structure in the `docs` folder, but you can customize it by editing `sidebars.ts` in the project root.

   For example, you can manually add links to specific docs in the sidebar like this:

   ```ts
   module.exports = {
     docs: [
       {
         type: 'category',
         label: 'Getting Started',
         items: ['getting-started'],
       },
     ],
   };
   ```

### 8. **Build the Site**
   Once you’re satisfied with your documentation, you can build the static version of your website by running the following command:

   If you’re using npm:
   ```bash
   npm run build
   ```

   This will generate the static files in the `build` folder.

### 9. **Deploy the Site**
   You can deploy the site anywhere that supports static files. Common platforms include GitHub Pages, Netlify, or Vercel. Docusaurus provides an easy deployment command for GitHub Pages:

   ```bash
   GIT_USER=<Your GitHub Username> USE_SSH=true yarn deploy
   ```

   `You’ll need to set up your GitHub repository and configure it to work with the deploy command.`

### 10. **Configure Docusaurus**
   To customize the site’s settings, open `docusaurus.config.ts`. Here, you can set the site name, favicon, URL, theme, plugins, and more.

### Example Docusaurus Config (Basic):
```ts
module.exports = {
  title: 'My Docs Site',
  tagline: 'A Docusaurus Project',
  url: 'https://your-site.com',
  baseUrl: '/',
  favicon: 'img/favicon.ico',
  organizationName: 'your-org',
  projectName: 'my-docs-site',
  themeConfig: {
    navbar: {
      title: 'My Docs Site',
      logo: {
        alt: 'Site Logo',
        src: 'img/logo.svg',
      },
      items: [
        { to: 'docs/', label: 'Docs', position: 'left' },
        { to: 'blog', label: 'Blog', position: 'left' },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Docs',
          items: [
            {
              label: 'Getting Started',
              to: 'docs/getting-started',
            },
          ],
        },
      ],
    },
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          sidebarPath: require.resolve('./sidebars.ts'),
        },
        blog: {
          showReadingTime: true,
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
};
``` 


## Useful Documentation On Crating A Docusaurus Site

### `index.md` (Docusaurus Overview Page)
```md
---
id: docusaurus-tools-overview
title: Comprehensive Guide to Docusaurus Tools
sidebar_label: Docusaurus Tools Overview
---

# Welcome to Docusaurus Documentation

This guide will provide a detailed overview of the various tools, components, and features available in Docusaurus for building high-quality documentation and static websites.

---

## 1. **Markdown & MDX Support**

Docusaurus allows you to write your documentation in **Markdown** and **MDX**. MDX extends Markdown by allowing you to include React components inside Markdown files.

### Example Markdown (`.md`) File:
```md
# Welcome to the Docs

This is a simple Markdown file.

## Subheading

More detailed information.
```

### Example MDX (`.mdx`) File:
```mdx
import MyComponent from '@site/src/components/MyComponent';

# Using React Components in MDX

This is a documentation file where you can include React components:

<MyComponent />
```

---

## 2. **Code Blocks**

Docusaurus supports code blocks with syntax highlighting, which is great for showing code examples in your documentation.

### Example Code Block:
\```ts
// JavaScript code block example
function greet(name) {
  return `Hello, ${name}!`;
}
\```

---

## 3. **Live Code Editing**

Docusaurus also supports **live code editing** with React components in MDX files, allowing users to interact with the code.

### Example of Live Code Editing:
```mdx
import React, { useState } from 'react';

export default function Counter() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>Click me</button>
    </div>
  );
}
```

This code renders a live counter component right in your documentation.

---

## 4. **Table of Contents (TOC)**

Docusaurus automatically generates a table of contents for your documentation pages based on headings (`h1`, `h2`, etc.).

To customize the TOC, you can use the following MDX options:

```md
---
id: page-id
title: Page Title
hide_table_of_contents: false
---
```

---

## 5. **Custom Components**

You can create custom React components to extend the functionality of your documentation pages. These components can be reused across multiple pages.

### Example of a Custom Component:

Create a file at `src/components/MyComponent.ts`:

```ts
import React from 'react';

function MyComponent() {
  return <div>This is a reusable component!</div>;
}

export default MyComponent;
```

In your MDX file, import and use the component:

```mdx
import MyComponent from '@site/src/components/MyComponent';

# Using Custom Components

<MyComponent />
```

---

## 6. **Images and Assets**

You can include images and other static assets in your documentation by placing them in the `static` folder.

### Example of Including an Image:
```mdx
![Docusaurus Logo](./img/docusaurus.png)
```

---

## 7. **Customizing the Sidebar**

Docusaurus allows you to automatically generate or manually customize the sidebar navigation. Sidebars are configured in the `sidebars.ts` file.

### Example Sidebar (`sidebars.ts`):
```ts
module.exports = {
  docs: [
    {
      type: 'category',
      label: 'Getting Started',
      items: ['introduction', 'installation'],
    },
    {
      type: 'category',
      label: 'Guides',
      items: ['guide-1', 'guide-2'],
    },
  ],
};
```

You can also customize sidebars on a per-category or per-page basis.

---

## 8. **Versioning Documentation**

Docusaurus allows you to version your documentation. To enable versioning, you run the following command:

```bash
npm run docusaurus docs:version 1.0.0
```

This will create a new versioned copy of your documentation under `versioned_docs/version-1.0.0`.

---

## 9. **Blogging Support**

Docusaurus supports blogs as well. You can create blog posts using Markdown or MDX in the `blog` directory.

### Example Blog Post:
```md
---
title: My First Blog Post
slug: my-first-blog-post
date: 2023-10-01
tags: [introduction, docusaurus]
---

# My First Blog Post

This is an example blog post written in Markdown.
```

---

## 10. **Search**

Docusaurus integrates with **Algolia Search**, providing a powerful search feature for your documentation. You can configure search in `docusaurus.config.ts`:

```ts
themeConfig: {
  algolia: {
    apiKey: 'YOUR_API_KEY',
    indexName: 'YOUR_INDEX_NAME',
  },
},
```

---

## 11. **Custom Pages**

In addition to documentation and blogs, Docusaurus allows you to create custom pages using React. These pages live in the `src/pages` directory.

### Example Custom Page:
```ts
import React from 'react';

function CustomPage() {
  return (
    <div>
      <h1>Welcome to the Custom Page!</h1>
      <p>This is a custom page built with React.</p>
    </div>
  );
}

export default CustomPage;
```

You can visit the custom page at `/custom-page`.

---

## 12. **Theming and Custom CSS**

Docusaurus provides a default theme that you can customize or replace with your own theme. You can inject custom CSS by adding styles to `src/css/custom.css`.

### Example Custom CSS:
```css
body {
  background-color: #f4f4f4;
}
```

You can also create custom themes using React components to fully modify the appearance of your site.

---

## 13. **Deployment**

Docusaurus generates static files, making it easy to deploy your site. Popular deployment platforms include **GitHub Pages**, **Netlify**, and **Vercel**.

### Example Deployment to GitHub Pages:
```bash
GIT_USER=<Your GitHub Username> USE_SSH=true yarn deploy
```

This will push your build to the `gh-pages` branch of your GitHub repository.

---

## 14. **Plugins**

Docusaurus supports plugins to extend functionality. Some popular built-in plugins include:

- **Google Analytics**: Tracks visitor traffic.
- **PWA Support**: Adds Progressive Web App capabilities.

You can add plugins to your `docusaurus.config.ts` like this:

```ts
plugins: [
  '@docusaurus/plugin-google-analytics',
  {
    resolve: '@docusaurus/plugin-pwa',
    options: {
      offlineModeActivationStrategies: ['queryString', 'always'],
    },
  },
],
```

---

## 15. **API Documentation with OpenAPI**

You can use Docusaurus to automatically generate API documentation from OpenAPI specs.

### Example:
Place your OpenAPI specification in the `static` directory and render it using a plugin.

```ts
plugins: ['docusaurus-plugin-openapi'],
```

---

## 16. **Environment Variables**

You can define and access environment variables in Docusaurus by adding them to the `.env` file at the root of your project.

### Example `.env`:
```bash
API_KEY=your_api_key
```

Access the environment variables in your configuration files or components:

```ts
process.env.API_KEY;
```

---

With these tools, Docusaurus provides a powerful and customizable framework for building documentation websites. You can extend and modify each part of the system using React, CSS, and plugins.
```

This file outlines the essential tools and components Docusaurus provides for building a robust documentation site. You can add this to your Docusaurus project as a guide to reference while building your site.