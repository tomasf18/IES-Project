import {
  Header,
  Footer,
  HomeSection,
  ServiceSection,
  FeatureSection,
  ProductSection,
  SecuritySection,
} from "../components";

export default function LandingPage() {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />

      <main className="flex-grow">
        <section id="home">
          <HomeSection />
        </section>
        <section id="service">
          <ServiceSection />
        </section>
        <div className="pr-16 pl-16">
          <hr className="border-2 border-gray-200" />
        </div>
        <section id="feature">
          <FeatureSection />
        </section>
        <section id="product">
          <ProductSection />
        </section>
        <section id="security">
          <SecuritySection />
        </section>
      </main>

      <Footer />
    </div>
  );
}
