import {
    ListFilter,
    MoreHorizontal,
    PlusCircle,
} from "lucide-react"

import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    DropdownMenu,
    DropdownMenuCheckboxItem,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"
import {
    Tabs,
    TabsContent,
} from "@/components/ui/tabs"

import apiClient from "@/data/apiClient";
import { useQuery } from "@tanstack/react-query";
import { useCallback } from "react"
import { useAuthStore } from "@/store/authStore"
import { GoTrash } from "react-icons/go";
import { useState } from "react";
import {
    AlertDialog,
    AlertDialogCancel,
    AlertDialogContent,
    AlertDialogDescription,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogTitle,
} from "@/components/ui/alert-dialog";


function LandingPage() {

    const auth = useAuthStore();
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const wordsQuery = useQuery({
        queryKey: ["word"],
        queryFn: async() =>{
            const res = await apiClient.wordsApi.apiWordsAllGet()
            return res.data
        },
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
                            onClick={() => {
                                
                            }}
                        >
                            Delete
                        </Button>
                    </AlertDialogFooter>
                </AlertDialogContent>
            </AlertDialog>
        );
    }

    const buildWordsTable = useCallback(() => {
        return(
            wordsQuery.data?.map((word) => {
                return(
                    <TableRow>
                        <TableCell className="text-left">
                            {word.id}
                        </TableCell>
                        <TableCell className="text-left">
                            {word.english}
                        </TableCell>
                        <TableCell className="text-left">
                            {word.italian}
                        </TableCell>
                        <TableCell className="text-left">
                            {word.category}
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
                    </TableRow>
                )
            }
        )
        );
    }, [wordsQuery.data])


    return (
            <div>
                <main>
                    <Tabs defaultValue="all">
                        <div className="flex items-center">
                            { auth.user && (<div className="ml-auto flex items-center gap-2">
                                <Button size="sm" className="h-8 gap-1">
                                    <PlusCircle className="h-3.5 w-3.5" />
                                    <span className="sr-only sm:not-sr-only sm:whitespace-nowrap">
                                        Add Product
                                    </span>
                                </Button>
                            </div>)}
                        </div>
                        <TabsContent value="all">
                            <Card x-chunk="dashboard-06-chunk-0">
                                <CardHeader>
                                    <CardTitle>Words</CardTitle>
                                    <CardDescription>
                                        Manage your words.
                                    </CardDescription>
                                </CardHeader>
                                <CardContent>
                                    <Table>
                                        <TableHeader>
                                            <TableRow>
                                            <TableHead className="w-[150px]">Number</TableHead>
                                            <TableHead className="w-[150px]">English</TableHead>
                                            <TableHead className="w-[150px]">Italian</TableHead>
                                            <TableHead className="w-[150px]">Category</TableHead>
                                            </TableRow>
                                        </TableHeader>
                                        <TableBody>
                                            {buildWordsTable()}
                                        </TableBody>
                                    </Table>
                                </CardContent>
                            </Card>
                        </TabsContent>
                    </Tabs>
                </main>
            {buildDeleteTypeDialog()}
            </div>
    )
}

export default LandingPage
