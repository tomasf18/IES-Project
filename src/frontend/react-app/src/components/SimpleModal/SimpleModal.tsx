import { Button, Modal } from "flowbite-react";
import { ReactNode } from "react";

interface SimpleModalProps {
  show: boolean;
  onClose: () => void;
  content: ReactNode;
  buttonText: string;
  buttonClass: string;
  onConfirm: () => void;
}

export default function SimpleModal({
  show,
  onClose,
  content,
  buttonText,
  buttonClass,
  onConfirm,
}: SimpleModalProps) {
  return (
    <Modal show={show} size="lg" onClose={onClose} popup>
      <Modal.Header />
      <Modal.Body>
        <div className="text-center">
          <div className="mb-3">{content}</div>
          <div className="flex justify-center items-center gap-4 mt-6">
          <button className={`w-100 py-3 justify-center items-center transition duration-300 ${buttonClass}`} onClick={onConfirm}>
            <strong>{buttonText}</strong>
          </button>
          </div>
        </div>
      </Modal.Body>
    </Modal>
  );
}