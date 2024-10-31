import { Header, Footer } from "../components";
import DataGraph from "../assets/undraw_visual_data_re_mxxo.svg";

export default function LandingPage() {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />

      {/* Conteúdo da página que se expande */}
      <main className="flex-grow">
        {/* Adicione aqui o conteúdo principal da sua LandingPage */}
        <section className="flex flex-col items-center bg-gray-100 p-16 pb-8 relative">
          <div className="container mx-auto flex flex-col lg:flex-row items-center gap-8">
            {/* Lado esquerdo: Título, texto e botão */}
            <div className="flex-1 flex flex-col gap-10 text-center lg:text-left">
              <h1 className="text-4xl md:text-5xl xl:text-6xl font-bold text-gray-800">
                Reach your full potential{" "}
                <span className="text-green-primary">
                  analyzing real-time data
                </span>
              </h1>
              <p className="text-gray-600">
                Where to grow your players' performance: history and real-time?
              </p>
              <button className="w-1/2 md:w-1/3 mt-4 px-6 py-3 bg-green-primary text-white font-semibold rounded-md hover:bg-green-darker transition duration-400">
                Register a Team
              </button>
            </div>

            {/* Lado direito: Imagem */}
            <div className="flex-1 flex justify-center items-center">
              <img
                src={DataGraph}
                alt="Data Graph Illustration"
                className="max-w-sm md:max-w-md lg:max-w-lg object-contain"
              />
            </div>
          </div>

          {/* Linha verde no final, centralizada */}
          <div className="w-1/12 h-1 bg-green-primary mt-16" />
        </section>
      </main>

      <Footer />
    </div>
  );
}
