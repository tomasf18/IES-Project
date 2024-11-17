import { Button } from "flowbite-react";

// Defina um tema base para o botão
const baseTheme = {
  color: {
    custom: "", // Este campo será configurado dinamicamente com base na cor passada
  },
};

// Mapeamento de estilos de cor
const colorStyles = {
  primary:
    "border border-transparent bg-green-primary text-white focus:ring-4 focus:ring-green-300 enabled:hover:bg-green-darker enabled:hover:text-white",
  secondary:
    "border border-transparent bg-white text-green-primary focus:ring-4 focus:ring-green-300 enabled:hover:bg-green-primary enabled:hover:text-white",
  gray: "border border-transparent bg-gray-400 text-white focus:ring-4 focus:ring-gray-300 enabled:hover:bg-gray-500 enabled:hover:text-white",
  white:
    "border border-black text-gray-800 focus:ring-4 focus:ring-gray-300 enabled:hover:bg-gray-400 enabled:hover:border-transparent enabled:hover:text-white",
};

type ColorKey = keyof typeof colorStyles;

export default function CustomButton({
  color = "primary",
  className = "",
  children,
  onClick,
  ...props
}: {
  color?: ColorKey;
  className?: string;
  children: React.ReactNode;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}) {
  const customTheme = {
    ...baseTheme,
    color: {
      custom: colorStyles[color],
    },
  };

  return (
    <Button theme={customTheme} color="custom" className={className} onClick={onClick} {...props} size="lg">
      {children}
    </Button>
  );
}
