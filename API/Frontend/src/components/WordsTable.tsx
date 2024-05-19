import { Button } from "@/components/ui/button"
import apiClient from "@/data/apiClient";
import { useAuthStore } from "@/store/authStore"
import { GoTrash } from "react-icons/go";
import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { useToast } from "@/components/ui/use-toast";
import { Word } from "@/apiClient";
import {
    AlertDialog,
    AlertDialogCancel,
    AlertDialogContent,
    AlertDialogDescription,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogTitle,
} from "@/components/ui/alert-dialog";
import {
    TableCell,
    TableRow,
} from "@/components/ui/table"


interface WordProps {
    type: Word;
}

function CreateTable(props: WordProps) {
    const auth = useAuthStore();
    const navigate = useNavigate();
    const { toast } = useToast();
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    const deleteWordMutation = useMutation({
        mutationFn: async (id: number) => {
            await apiClient.wordsApi.apiWordsIdDelete(id)
        },
        onSuccess: () => {
            toast({
                title: "Word deleted",
                description: "The word has been deleted successfully",
                variant: "success",
            });
            navigate(0);
        },
        onError: (error) => {
            toast({
                title: "Error",
                description: error.message,
                variant: "destructive",
            });
        }
    });
    const buildDeleteTypeDialog = () => {
        return (
            <AlertDialog open={deleteDialogOpen} onOpenChange={setDeleteDialogOpen}>
                <AlertDialogContent>
                    <AlertDialogHeader>
                        <AlertDialogTitle>Delete word</AlertDialogTitle>
                    </AlertDialogHeader>
                    <AlertDialogDescription>
                        Are you sure you want to delete this word?
                    </AlertDialogDescription>
                    <AlertDialogFooter>
                        <AlertDialogCancel>Cancel</AlertDialogCancel>
                        <Button
                            variant="destructive"
                            loading={deleteWordMutation.isPending}
                            onClick={() => {
                                deleteWordMutation.mutate(props.type.id);
                            }}
                        >
                            Delete
                        </Button>
                    </AlertDialogFooter>
                </AlertDialogContent>
            </AlertDialog>
        );
    }

        return (
            <TableRow>
                <TableCell className="text-left">
                    {props.type.id}
                </TableCell>
                <TableCell className="text-left">
                    {props.type.english}
                </TableCell>
                <TableCell className="text-left">
                    {props.type.italian}
                </TableCell>
                <TableCell className="text-left">
                    {props.type.category}
                </TableCell>
                {auth.user && (
                    <TableCell>

                        <Button
                            variant="ghost"
                            className="text-destructive text-2xl"
                            onClick={() => setDeleteDialogOpen(true)}>
                            <GoTrash />
                        </Button>

                    </TableCell>
                )}
                {buildDeleteTypeDialog()}
            </TableRow>
        )
}


export default CreateTable;