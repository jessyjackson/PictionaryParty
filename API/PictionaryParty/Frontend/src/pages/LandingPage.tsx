import {
    PlusCircle,
} from "lucide-react"
import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    Table,
    TableBody,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"
import {
    Tabs,
    TabsContent,
} from "@/components/ui/tabs"

import apiClient from "@/data/apiClient";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useAuthStore } from "@/store/authStore"
import { CreateWordRequest, Word } from "@/apiClient";
import CreateTable from "@/components/WordsTable";
import { useState } from "react";

import { toast } from "@/components/ui/use-toast"
import { useNavigate } from "react-router-dom"
import { Input } from "@/components/ui/input"

import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
} from "@/components/ui/dialog";


function LandingPage() {
    const [addWordDialogOpen, setAddWordDialogOpen] = useState(false);
    const [english, setEnglish] = useState("");
    const [italian, setItalian] = useState("");
    const [category, setCategory] = useState("");
    const auth = useAuthStore();
    const navigate = useNavigate();

    const wordsQuery = useQuery({
        queryKey: ["word"],
        queryFn: async() =>{
            const res = await apiClient.wordsApi.apiWordsAllGet()
            return res.data
        },
    });
    const addWordMutation = useMutation({
        mutationFn: async (word: CreateWordRequest) => {
            if(!word.english || !word.italian || !word.category) {
                throw new Error("All fields are required")
            }
            await apiClient.wordsApi.apiWordsPost(word)
        },
        onSuccess: () => {
            toast({
                title: "Word added",
                description: "The word has been added successfully",
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

    const buildAddWordDialog = () => {
        return (
            <Dialog open={addWordDialogOpen} onOpenChange={setAddWordDialogOpen}>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>Add Word</DialogTitle>
                        <DialogDescription>
                            Add a new word to the list.
                        </DialogDescription>
                    </DialogHeader>
                    <Input
                        placeholder="English"
                        className="mt-4"
                        value={english}
                        onChange={(e) => setEnglish(e.target.value)}
                    />
                    <Input
                        placeholder="Italian"
                        className="mt-4"
                        value={italian}
                        onChange={(e) => setItalian(e.target.value)}
                    />
                    <Input
                        placeholder="Category"
                        className="mt-4"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                    />
                    <Button
                        className="mt-4"
                        loading={addWordMutation.isPending}
                        onClick={() => {
                            addWordMutation.mutate({english, italian, category})
                        }}
                    >
                        Add Word
                    </Button>
                </DialogContent>
            </Dialog>
        )
    }

    return (
            <main className="page mt-16">
                    <Tabs defaultValue="all">
                        <div className="flex items-center">
                            { auth.user && (<div className="ml-auto flex items-center gap-2">
                                <Button size="sm" className="h-8 gap-1" onClick={() =>{
                                    setAddWordDialogOpen(true)
                                    setEnglish("")
                                    setItalian("")
                                    setCategory("")
                                }}>
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
                                            <TableHead className="">Number</TableHead>
                                            <TableHead className="">English</TableHead>
                                            <TableHead className="">Italian</TableHead>
                                            <TableHead className="">Category</TableHead>
                                        {auth.user && (<TableHead className="">Delete</TableHead>)}
                                            </TableRow>
                                        </TableHeader>
                                        <TableBody>
                                            {wordsQuery.data?.map((word: Word) => (
                                                <CreateTable key={word.id} type={word} />
                                            ))}
                                        </TableBody>
                                    </Table>
                                </CardContent>
                            </Card>
                        </TabsContent>
                    </Tabs>
                {buildAddWordDialog()}
            </main>
                
        )
}

export default LandingPage
