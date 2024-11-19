import { Table, TableBody, TableCell, TableHead, TableHeadCell, TableRow } from "flowbite-react";

interface StripedTableProps {
  widthClass?: string;
  heightClass?: string;
  columnsName: React.ReactNode[];
  rows: React.ReactNode[][];
}

export default function StripedTable({ widthClass, heightClass, columnsName, rows }: StripedTableProps) {
  return (
    <div className={`overflow-x-auto ${widthClass} ${heightClass}`}>
      <Table striped>
        {columnsName && 
          <TableHead>
            {columnsName.map((columnName, index) => (
                <TableHeadCell className="text-center text-xl" key={index}>{columnName}</TableHeadCell>
            ))}
          </TableHead>
        }

        <TableBody className="divide-y">
          {rows.map((row, rowIndex) => (
            <TableRow key={rowIndex} className="bg-white dark:border-gray-700 dark:bg-gray-800">
              {row.map((cell, cellIndex) => (
                <TableCell key={cellIndex} className="text-center text-lg whitespace-nowrap font-medium text-gray-900 dark:text-white">
                  {cell}
                </TableCell>
              ))}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}