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
  const links = [
    { href: "#home", label: "Home" },
    { href: "#service", label: "Service" },
    { href: "#feature", label: "Feature" },
    { href: "#product", label: "Product" },
    { href: "#security", label: "Security" },
  ];

  const buttons: { to: string; label: string; color: "primary" | "secondary" }[] = [
    { to: "/login", label: "Login", color: "secondary" },
    { to: "/signup", label: "Sign Up", color: "primary" },
  ];

  return (
    <div className="flex flex-col min-h-screen">
      <Header links={links} buttons={buttons}/>

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
