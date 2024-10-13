import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

type FeatureItem = {
  title: string;
  Svg: React.ComponentType<React.ComponentProps<'svg'>>;
  description: JSX.Element;
};

const FeatureList: FeatureItem[] = [
  {
    title: 'Real-Time Data Monitoring',
    Svg: require('@site/static/img/undraw_fitness_stats_sht6.svg').default,
    description: (
      <>
        Track player statistics like heart rate, fatigue levels, and performance metrics in real-time, providing actionable insights during training and matches.
      </>
    ),
  },
  {
    title: 'Role-Based Access Control',
    Svg: require('@site/static/img/undraw_team_spirit_re_yl1v.svg').default,
    description: (
      <>
        Manage permissions efficiently with role-based access for coaches, team directors, personal trainers, and players, ensuring secure and organized data access.
      </>
    ),
  },
  {
    title: 'In-Game Metrics Analysis',
    Svg: require('@site/static/img/undraw_all_the_data_re_hh4w.svg').default,
    description: (
      <>
        Monitor in-game metrics like ball possession and shots on target, allowing coaches to make data-driven decisions for real-time tactical adjustments.
      </>
    ),
  },
];

function Feature({title, Svg, description}: FeatureItem) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures(): JSX.Element {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
