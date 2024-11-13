import {
	ConfigurationCard,
	StripedTable
  } from "../components";

export default function TestPage() {

	let widthClass = "w-[60rem]";
	let heightClass = "h-[40rem]";
	let name = "António Mendes";
	let widthClass2 = "w-full";
	let heightClass2 = "h-full";
	let columnsName = ["Product name", "Color", "Category", <span className="sr-only">Edit</span>];
	let rows = [
		["Apple MacBook Pro 17\"", "Sliver", "Laptop", "$2999"],
		["Microsoft Surface Pro", "White", "Laptop PC", "$1999"],
		["Magic Mouse 2", "Black", "Accessories", "$99"],
		["Google Pixel Phone", "Gray", "Phone", "$799"],
		["Google Pixel Phone", "Gray", "Phone", "$799"],
		["Google Pixel Phone", "Gray", "Phone", "$799"],
		["Google Pixel Phone", "Gray", "Phone", "$799"],
	];

	let rightContent =
		<div className="flex flex-col min-h-screen p-4">
			<StripedTable 
				widthClass={widthClass2} 
				heightClass={heightClass2}
				columnsName={columnsName}
				rows={rows}
			/>
		</div>

	return (
		<div className="flex flex-col min-h-screen m-10">
			<ConfigurationCard
			widthClass={widthClass}
			heightClass={heightClass}
			name={name}
			rightContent={rightContent}
		/>
		</div>
	)
}