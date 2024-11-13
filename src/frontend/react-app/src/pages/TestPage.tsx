import {
	StripedTable
  } from "../components";

export default function TestPage() {

	let widthClass = "w-1/2";
	let heightClass = "h-1/2";
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

	return (
		<div className="flex flex-col min-h-screen p-4">
			<StripedTable 
				widthClass={widthClass} 
				heightClass={heightClass}
				columnsName={columnsName}
				rows={rows}
			/>
		</div>
	);
}