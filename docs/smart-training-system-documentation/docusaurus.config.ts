import {themes as prismThemes} from 'prism-react-renderer';
import type {Config} from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

const config: Config = {
  title: 'Smart Training System Documentation',
  tagline: 'From Data to Dominance: Enhance Every Game!',
  favicon: 'img/logo_icon.png',
  url: 'https://smart-training-system-docs.com',
  baseUrl: '/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'deti', // Usually your GitHub org/user name.
  projectName: 'smart-training-system', // Usually your repo name.

  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',

  // Even if you don't use internationalization, you can use this field to set
  // useful metadata like html lang. For example, if your site is Chinese, you
  // may want to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'en',
    locales: ['en', 'pt'],
  },

  presets: [
    [
      'classic',
      {
        docs: {
          sidebarPath: './sidebars.ts',
          editUrl:
            'https://github.com/detiuaveiro/ies-24-25-group-project-201',
        },
        theme: {
          customCss: './src/css/custom.css',
        },
      } satisfies Preset.Options,
    ],
  ],

  themeConfig: {
    // Replace with your project's social card
    image: 'img/docusaurus-social-card.jpg',
    navbar: {
      title: 'STS Docs',
      logo: {
        alt: 'My Site Logo',
        src: 'img/logo.png',
      },
      items: [
        {
          type: 'docSidebar',
          sidebarId: 'tutorialSidebar',
          position: 'left',
          label: 'Documentation',
        },
        {
          href: '#',
          label: 'Our Website',
          position: 'right',
        },
        {
          href: 'https://github.com/detiuaveiro/ies-24-25-group-project-201',
          label: 'GitHub',
          position: 'right',
        },
        {
          href: 'https://github.com/orgs/detiuaveiro/projects/31',
          label: 'Backlog',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      copyright: `STS Copyright © ${new Date().getFullYear()}`,   
    },
    prism: {
      theme: prismThemes.github,
      darkTheme: prismThemes.dracula,
    },
  } satisfies Preset.ThemeConfig,
};

export default config;
